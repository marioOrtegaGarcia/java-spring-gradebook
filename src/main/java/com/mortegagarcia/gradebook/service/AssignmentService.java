package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private AssignmentConverter aConv;

	public List<AssignmentDTO> findAll() {
		List<Assignment> assignmentEntities = assignmentRepository.findAll();
		return aConv.entityToDTO(assignmentEntities);
	}

	public AssignmentDTO getOne(int id) {
		Assignment assignmentEntity = assignmentRepository.getOne(id);
		return aConv.entityToDTO(assignmentEntity);
	}

	public AssignmentDTO save(AssignmentDTO assignmentDTO) {
		Assignment assignmentEntity = saveNewEntity(assignmentDTO);
		return aConv.entityToDTO(assignmentEntity);
	}

	public AssignmentDTO findById(Integer id) {
		Assignment assignmentEntity = assignmentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		return aConv.entityToDTO(assignmentEntity);
	}

	public List<AssignmentDTO> findAssignmentByCourseID(Integer id) {
		List<Assignment> assignmentEntities = assignmentRepository.findAssignmentByCourseID(id);
		return aConv.entityToDTO(assignmentEntities);
	}

	public List<AssignmentDTO> findAssignmentsByStudentID(Integer id) {
		List<Assignment> assignmentEntities = assignmentRepository.findAssignmentsByStudentID(id);
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
