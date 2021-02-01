package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import com.mortegagarcia.gradebook.repository.GradeRepository;
import com.mortegagarcia.gradebook.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {

	private final GradeRepository gradeRepository;
	private final StudentRepository studentRepository;
	private final AssignmentRepository assignmentRepository;
	private final GradeConverter gConv;

	@PreAuthorize("hasRole('ADMIN')")
	public List<GradeDTO> findAll() {
		List<Grade> gradeEntities = gradeRepository.findAll();
		return gConv.entityToDTO(gradeEntities);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToGradeWithID(authentication, #gradeDTO.id))")
	public GradeDTO save(GradeDTO gradeDTO) {
		Grade gradeEntity = saveNewGrade(gradeDTO);
		return gConv.entityToDTO(gradeEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToGradeWithID(authentication, #gradeID))")
	public GradeDTO getOne(int gradeID) {
		Grade gradeEntity = gradeRepository.getOne(gradeID);
		return gConv.entityToDTO(gradeEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToGradeWithID(authentication, #gradeID))")
	public GradeDTO findById(Integer gradeID) {
		Grade gradeEntity = gradeRepository.findById(gradeID)
				.orElseThrow(IllegalArgumentException::new);
		return gConv.entityToDTO(gradeEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToGradeWithID(authentication, #gradeDTO.id))")
	public void delete(GradeDTO gradeDTO) {
		Grade gradeEntity = gradeRepository.findById(gradeDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		gradeRepository.delete(gradeEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToGradeWithID(authentication, #gradeID))")
	public GradeDTO update(Integer gradeID, GradeDTO dto) {
		Grade gradeEntity = gradeRepository.findById(gradeID).orElse(null);
		if (gradeEntity == null) gradeEntity = saveNewGrade(dto);
		else gradeEntity = saveExistingGrade(gradeEntity, dto);
		return gConv.entityToDTO(gradeEntity);
	}

	private Grade saveExistingGrade(Grade gradeEntity, GradeDTO gradeDTO) {
		gradeEntity.setScore(gradeDTO.getScore());
		if (gradeEntity.getStudent().getId() != gradeDTO.getStudentID()) {
			Student studentEntity = studentRepository.findById(gradeDTO.getStudentID())
					.orElseThrow(IllegalArgumentException::new);
			gradeEntity.setStudent(studentEntity);
		}
		if (gradeEntity.getAssignment().getId() != gradeDTO.getAssignmentID()) {
			Assignment assignmentEntity = assignmentRepository.findById(gradeDTO.getAssignmentID())
					.orElseThrow(IllegalArgumentException::new);
			gradeEntity.setAssignment(assignmentEntity);
		}
		return gradeEntity;
	}

	private Grade saveNewGrade(GradeDTO gradeDTO) {
		Grade gradeEntity = gConv.dtoToEntity(gradeDTO);
		Student studentEntity = studentRepository.findById(gradeDTO.getStudentID())
				.orElseThrow(IllegalArgumentException::new);
		gradeEntity.setStudent(studentEntity);
		Assignment assignmentEntity = assignmentRepository.findById(gradeDTO.getAssignmentID())
				.orElseThrow(IllegalArgumentException::new);
		gradeEntity.setAssignment(assignmentEntity);
		gradeEntity = gradeRepository.save(gradeEntity);
		return gradeEntity;
	}

}
