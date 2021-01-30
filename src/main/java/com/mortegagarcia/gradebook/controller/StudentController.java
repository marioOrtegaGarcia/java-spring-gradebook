package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.service.AssignmentService;
import com.mortegagarcia.gradebook.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private AssignmentService assignmentService;

	@GetMapping("all")
	public ResponseEntity<List<StudentDTO>> getStudents() {
		List<StudentDTO> studentDTO = studentService.findAll();
		return (studentDTO == null || studentDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}

	// CREATE
	@PostMapping(consumes = {"application/json"})
	public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student) {
		StudentDTO studentDTO = studentService.save(student);
		return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}

	// READ
	@GetMapping("{studentID}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer studentID) {
		StudentDTO studentDTO = studentService.findById(studentID);
		return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}

	@GetMapping("{studentID}/assignment/{assignmentID}/Grade")
	public ResponseEntity<GradeDTO> getStudentAssignmentGrade(@PathVariable Integer studentID, @PathVariable Integer assignmentID) {
		GradeDTO gradeDTO = studentService.getStudentAssignmentGrade(studentID, assignmentID);
		return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(gradeDTO, HttpStatus.OK);
	}

	@GetMapping("{studentID}/assignment/{assignmentID}/Grade/Letter")
	public ResponseEntity<Character> getStudentAssignmentLetterGrade(@PathVariable Integer studentID, @PathVariable Integer assignmentID) {
		Character letterGrade = studentService.getStudentAssignmentLetterGrade(studentID, assignmentID);
		return (letterGrade == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(letterGrade, HttpStatus.OK);
	}

	@GetMapping("{studentID}/assignment/all/")
	public ResponseEntity<List<AssignmentDTO>> findAssignmentsByStudentID(@PathVariable Integer studentID) {
		List<AssignmentDTO> assignmentDTO = assignmentService.findAssignmentsByStudentID(studentID);
		return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
	}


	@GetMapping("{studentID}/assignment/all/Grade/Missing")
	public ResponseEntity<List<AssignmentDTO>> getStudentAssignmentMissingGrade(@PathVariable Integer studentID) {
		List<AssignmentDTO> assignmentDTO = studentService.getStudentAssignmentsMissingGrade(studentID);
		return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
	}

	// UPDATE
	@PutMapping(path = "{{studentID}}", consumes = {"application/json"})
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Integer studentID) {
		StudentDTO studentDTO = studentService.update(studentID, dto);
		return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}

	// DELETE
	@DeleteMapping("{studentID}")
	public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer studentID) {
		StudentDTO deleted = studentService.getOne(studentID);
		if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		studentService.delete(deleted);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

}
