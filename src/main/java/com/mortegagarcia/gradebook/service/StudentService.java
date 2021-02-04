package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Email;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;
import com.mortegagarcia.gradebook.repository.EmailRepository;
import com.mortegagarcia.gradebook.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	private final EmailRepository emailRepository;
	private final StudentConverter sConv;
	private final AssignmentConverter aConv;
	private final GradeConverter gConv;

	@PreAuthorize("hasRole('ADMIN')")
	public List<StudentDTO> findAll() {
		List<Student> studentEntities = studentRepository.findAll();
		return sConv.entityToDTO(studentEntities);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public StudentDTO save(StudentDTO studentDTO) {
		Student studentEntity = saveNewEntity(studentDTO);
		return sConv.entityToDTO(studentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasAnyRole('PROFESSOR', 'STUDENT') and @userSecurity.hasAccessToStudentWithID(authentication, #studentID))")
	public StudentDTO getOne(int studentID) {
		Student studentEntity = studentRepository.getOne(studentID);
		return sConv.entityToDTO(studentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasAnyRole('PROFESSOR', 'STUDENT') and @userSecurity.hasAccessToStudentWithID(authentication, #studentID))")
	public StudentDTO findById(Integer studentID) {
		Student studentEntity = studentRepository.findById(studentID)
				.orElseThrow(IllegalArgumentException::new);
		return sConv.entityToDTO(studentEntity);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public void delete(StudentDTO studentDTO) {
		Student studentEntity = studentRepository.findById(studentDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		studentRepository.delete(studentEntity);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public StudentDTO update(Integer studentID, StudentDTO studentDTO) {
		Student studentEntity = studentRepository.findById(studentID).orElse(null);
		if (studentEntity == null) studentEntity = saveNewEntity(studentDTO);
		else studentEntity = saveExistingEntity(studentEntity, studentDTO);
		return sConv.entityToDTO(studentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasAnyRole('PROFESSOR', 'STUDENT') and @userSecurity.hasAccessToStudentWithID(authentication, #studentID))")
	public GradeDTO getStudentAssignmentGrade(Integer studentID, Integer assignmentID) {
		Grade gradeDTO = studentRepository.getStudentAssignmentGrade(studentID, assignmentID);
		return gConv.entityToDTO(gradeDTO);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasAnyRole('PROFESSOR', 'STUDENT') and @userSecurity.hasAccessToStudentWithID(authentication, #studentID))")
	public Character getStudentAssignmentLetterGrade(Integer studentID, Integer assignmentID) {
		return studentRepository.getStudentAssignmentLetterGrade(studentID, assignmentID);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasAnyRole('PROFESSOR', 'STUDENT') and @userSecurity.hasAccessToStudentWithID(authentication, #studentID))")
	public List<AssignmentDTO> getStudentAssignmentsMissingGrade(Integer studentID) {
		List<Assignment> assignment = studentRepository.findAllByIdAndMissingAssignments(studentID)
				.orElseThrow(IllegalArgumentException::new);
		return aConv.entityToDTO(assignment);
	}

	private Student saveExistingEntity(Student studentEntity, StudentDTO studentDTO) {
		studentEntity.setFirstName(studentDTO.getFirstName());
		studentEntity.setLastName(studentDTO.getLastName());
		studentEntity.setGradeLevel(studentDTO.getGradeLevel());
		Email email = emailRepository.findEmailByEmail(studentDTO.getEmail())
				.orElse(new Email().setEmail(studentDTO.getEmail()));
		studentEntity.setEmail(email);
		studentEntity = studentRepository.save(studentEntity);
		return studentEntity;
	}

	private Student saveNewEntity(StudentDTO studentDTO) {
		Student studentEntity = sConv.dtoToEntity(studentDTO);
		studentEntity = studentRepository.save(studentEntity);
		return studentEntity;
	}
}
