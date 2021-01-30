package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseService service;

	// CREATE
	@PostMapping(consumes = {"application/json"})
	public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) {
		CourseDTO courseDTO = service.save(course);
		return (courseDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(courseDTO, HttpStatus.OK);
	}

	// READ
	@GetMapping("{courseID}")
	public ResponseEntity<CourseDTO> getCourse(@PathVariable Integer courseID) {
		CourseDTO course = service.findById(courseID);
		return (course == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("{courseID}/assignment/{assignmentID}")
	public ResponseEntity<AssignmentDTO> getCourseAssignment(@PathVariable Integer courseID, @PathVariable Integer assignmentID) {
		AssignmentDTO assignmentDTO = service.findCourseAssignment(courseID, assignmentID);
		return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
	}

	@GetMapping("{courseID}/assignment/all")
	public ResponseEntity<List<AssignmentDTO>> getCourseAssignments(@PathVariable Integer courseID) {
		List<AssignmentDTO> assignmentDTOS = service.getCourseAssignments(courseID);
		return (assignmentDTOS == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(assignmentDTOS, HttpStatus.OK);
	}

	@DeleteMapping("{courseID}/assignment/all")
	public ResponseEntity<List<AssignmentDTO>> deleteCourseAssignments(@PathVariable Integer courseID) {
		List<AssignmentDTO> assignmentDTOS = service.deleteCourseAssignments(courseID);
		return (assignmentDTOS == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(assignmentDTOS, HttpStatus.OK);
	}

	@GetMapping("all")
	public ResponseEntity<List<CourseDTO>> getCourses() {
		List<CourseDTO> coursesDTO = service.findAll();
		return (coursesDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}

	@GetMapping("all/professor/null")
	public ResponseEntity<List<CourseDTO>> findCourseWhereProfessorIsNull() {
		List<CourseDTO> coursesDTO = service.findCoursesWhereProfessorIsNull();
		return getListResponseEntity(coursesDTO);
	}

	@GetMapping("all/professor/{professorID}")
	public ResponseEntity<List<CourseDTO>> findCourseByProfessorID(@PathVariable Integer professorID) {
		List<CourseDTO> coursesDTO = service.findCoursesByProfessorID(professorID);
		return getListResponseEntity(coursesDTO);
	}


	// UPDATE
	@PutMapping(path = "{{courseID}}", consumes = {"application/json"})
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Integer courseID) {
		courseDTO = service.update(courseID, courseDTO);
		return (courseDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(courseDTO, HttpStatus.OK);
	}

	// DELETE
	@DeleteMapping("{courseID}")
	public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Integer courseID) {
		CourseDTO deleted = service.getOne(courseID);
		if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		service.delete(deleted);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

	private ResponseEntity<List<CourseDTO>> getListResponseEntity(List<CourseDTO> coursesDTO) {
		if (coursesDTO == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (coursesDTO.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}

}
