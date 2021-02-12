package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignmentService {

	private final AssignmentRepository assignmentRepository;
	private final CourseRepository courseRepository;
	private final AssignmentConverter aConv;

	public List<AssignmentDTO> findAll() {
		List<Assignment> assignmentEntities = assignmentRepository.findAll();
		return aConv.entityToDTO(assignmentEntities);
	}

	public AssignmentDTO getOne(int assignmentID) {
		Assignment assignmentEntity = assignmentRepository.getOne(assignmentID);
		return aConv.entityToDTO(assignmentEntity);
	}

	public AssignmentDTO save(AssignmentDTO assignmentDTO) {
		Assignment assignmentEntity = saveNewEntity(assignmentDTO);
		return aConv.entityToDTO(assignmentEntity);
	}

	public AssignmentDTO findById(Integer assignmentID) {
		Assignment assignmentEntity = assignmentRepository.findById(assignmentID).orElseThrow(IllegalArgumentException::new);
		return aConv.entityToDTO(assignmentEntity);
	}

	public List<AssignmentDTO> findAssignmentByCourseID(Integer assignmentID) {
		List<Assignment> assignmentEntities = assignmentRepository.findAssignmentByCourseID(assignmentID);
		return aConv.entityToDTO(assignmentEntities);
	}

	public List<AssignmentDTO> findAssignmentsByStudentID(Integer assignmentID) {
		List<Assignment> assignmentEntities = assignmentRepository.findAssignmentsByStudentID(assignmentID);
		return aConv.entityToDTO(assignmentEntities);
	}

	public void delete(AssignmentDTO assignmentDTO) {
		Assignment assignment = assignmentRepository.findById(assignmentDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		assignmentRepository.delete(assignment);
	}

	public AssignmentDTO update(Integer assignmentID, AssignmentDTO assignmentDTO) {
		Assignment assignmentEntity = assignmentRepository.findById(assignmentID).orElse(null);
		if (assignmentEntity == null) assignmentEntity = saveNewEntity(assignmentDTO);
		else assignmentEntity = saveExistingEntity(assignmentEntity, assignmentDTO);
		return aConv.entityToDTO(assignmentEntity);
	}

	public Double getAssignmentMinimumGrade(Integer assignmentID) {
		return assignmentRepository.getAssignmentMinimumGrade(assignmentID);
	}

	public Double getAssignmentAverageGrade(Integer assignmentID) {
		return assignmentRepository.getAssignmentAverageGrade(assignmentID);
	}

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
