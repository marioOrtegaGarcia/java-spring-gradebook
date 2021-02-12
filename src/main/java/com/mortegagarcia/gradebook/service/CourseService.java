package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.CourseConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseConverter cConv;
	private final AssignmentConverter aConv;

	public List<CourseDTO> findAll() {
		List<Course> courseEntities = courseRepository.findAll();
		return cConv.entityToDTO(courseEntities);
	}

	public CourseDTO save(CourseDTO courseDTO) {
		Course courseEntity = saveNewEntity(courseDTO);
		return cConv.entityToDTO(courseEntity);
	}

	public CourseDTO getOne(int courseID) {
		Course courseEntity = courseRepository.getOne(courseID);
		return cConv.entityToDTO(courseEntity);
	}

	public CourseDTO findById(Integer courseID) {
		Course courseEntity = courseRepository.findById(courseID)
				.orElseThrow(IllegalArgumentException::new);
		return cConv.entityToDTO(courseEntity);
	}

	public List<CourseDTO> findCoursesByProfessorID(Integer courseID) {
		List<Course> courseEntities = courseRepository.findCoursesByProfessorID(courseID)
				.orElseThrow(IllegalArgumentException::new);
		return cConv.entityToDTO(courseEntities);
	}

	public List<CourseDTO> findCoursesWhereProfessorIsNull() {
		List<Course> courseEntities = courseRepository.findCoursesWhereProfessorIsNull();
		return cConv.entityToDTO(courseEntities);
	}

	public void delete(CourseDTO courseDTO) {
		Course courseEntity = courseRepository.findById(courseDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		courseRepository.delete(courseEntity);
	}

	public CourseDTO update(Integer courseID, CourseDTO courseDTO) {
		Course courseEntity = courseRepository.findById(courseID).orElse(null);
		if (courseEntity == null) courseEntity = saveNewEntity(courseDTO);
		else courseEntity = saveExistingEntity(courseEntity, courseDTO);
		return cConv.entityToDTO(courseEntity);
	}

	public AssignmentDTO findCourseAssignment(Integer courseID, Integer assignmentID) {
		Assignment assignment = courseRepository.findCourseAssignment(courseID, assignmentID);
		return aConv.entityToDTO(assignment);
	}

	public List<AssignmentDTO> getCourseAssignments(Integer courseID) {
		List<Assignment> assignments = courseRepository.getCourseAssignments(courseID);
		return aConv.entityToDTO(assignments);
	}

	public List<AssignmentDTO> deleteCourseAssignments(Integer courseID) {
		List<AssignmentDTO> assignmentDTOS = aConv.entityToDTO(courseRepository.getCourseAssignments(courseID));
		courseRepository.deleteCourseAssignments(courseID);
		return assignmentDTOS;
	}

	private Course saveExistingEntity(Course courseEntity, CourseDTO courseDTO) {
		courseEntity.setName(courseDTO.getName());
		courseEntity.setAssignments(aConv.dtoToEntity(courseDTO.getAssignments()));
		courseEntity = courseRepository.save(courseEntity);
		return courseEntity;
	}

	private Course saveNewEntity(CourseDTO courseDTO) {
		Course courseEntity = cConv.dtoToEntity(courseDTO);
		courseEntity = courseRepository.save(courseEntity);
		return courseEntity;
	}

}
