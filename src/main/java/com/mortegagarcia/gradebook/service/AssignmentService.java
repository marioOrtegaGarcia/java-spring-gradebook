package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignmentService {

	private final AssignmentRepository assignmentRepository;
	private final CourseRepository courseRepository;
	private final AssignmentConverter aConv;

	@PreAuthorize("hasRole('ADMIN')")
	public List<AssignmentDTO> findAll() {
		List<Assignment> assignmentEntities = assignmentRepository.findAll();
		return aConv.entityToDTO(assignmentEntities);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public AssignmentDTO getOne(int assignmentID) {
		Assignment assignmentEntity = assignmentRepository.getOne(assignmentID);
		return aConv.entityToDTO(assignmentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentDTO.id))")
	public AssignmentDTO save(AssignmentDTO assignmentDTO) {
		Assignment assignmentEntity = saveNewEntity(assignmentDTO);
		return aConv.entityToDTO(assignmentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public AssignmentDTO findById(Integer assignmentID) {
		Assignment assignmentEntity = assignmentRepository.findById(assignmentID).orElseThrow(IllegalArgumentException::new);
		return aConv.entityToDTO(assignmentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public List<AssignmentDTO> findAssignmentByCourseID(Integer assignmentID) {
		List<Assignment> assignmentEntities = assignmentRepository.findAssignmentByCourseID(assignmentID);
		return aConv.entityToDTO(assignmentEntities);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public List<AssignmentDTO> findAssignmentsByStudentID(Integer assignmentID) {
		List<Assignment> assignmentEntities = assignmentRepository.findAssignmentsByStudentID(assignmentID);
		return aConv.entityToDTO(assignmentEntities);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentDTO.id))")
	public void delete(AssignmentDTO assignmentDTO) {
		Assignment assignment = assignmentRepository.findById(assignmentDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		assignmentRepository.delete(assignment);
	}

	@PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentDTO.id))")
	public AssignmentDTO update(Integer assignmentID, AssignmentDTO assignmentDTO) {
		Assignment assignmentEntity = assignmentRepository.findById(assignmentID).orElse(null);
		if (assignmentEntity == null) assignmentEntity = saveNewEntity(assignmentDTO);
		else assignmentEntity = saveExistingEntity(assignmentEntity, assignmentDTO);
		return aConv.entityToDTO(assignmentEntity);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public Double getAssignmentMinimumGrade(Integer assignmentID) {
		return assignmentRepository.getAssignmentMinimumGrade(assignmentID);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public Double getAssignmentAverageGrade(Integer assignmentID) {
		return assignmentRepository.getAssignmentAverageGrade(assignmentID);
	}

	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasAccessToAssignmentWithID(authentication, #assignmentID)")
	public Double getAssignmentMaximumGrade(Integer assignmentID) {
		return assignmentRepository.getAssignmentMaximumGrade(assignmentID);
	}

	private Assignment saveExistingEntity(Assignment assignmentEntity, AssignmentDTO assignmentDTO) {
		assignmentEntity.setName(assignmentDTO.getName());
		assignmentEntity.setPossibleScore(assignmentDTO.getPossibleScore());
		if (assignmentEntity.getCourse().getId() != assignmentDTO.getCourseID()) {
			Course courseEntity = courseRepository.findById(assignmentDTO.getCourseID())
					.orElseThrow(IllegalArgumentException::new);
			assignmentEntity.setCourse(courseEntity);
		}
		assignmentEntity = assignmentRepository.save(assignmentEntity);
		return assignmentEntity;
	}

	private Assignment saveNewEntity(AssignmentDTO assignmentDTO) {
		Assignment assignmentEntity = aConv.dtoToEntity(assignmentDTO);
		Course courseEntity = courseRepository.findById(assignmentDTO.getCourseID())
				.orElseThrow(IllegalArgumentException::new);
		assignmentEntity.setCourse(courseEntity);
		assignmentEntity = assignmentRepository.save(assignmentEntity);
		return assignmentEntity;
	}

}
