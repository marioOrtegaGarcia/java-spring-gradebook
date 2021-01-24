package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService service;


    @GetMapping("student/all")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDTO = service.findAll();
        return (studentDTO == null || studentDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/student", consumes = {"application/json"})
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student) {
        StudentDTO studentDTO = service.save(student);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("student/{studentID}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer studentID) {
        StudentDTO studentDTO = service.findById(studentID);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping("/student/{studentID}/assignment/{assignmentID}/Grade")
    public ResponseEntity<GradeDTO> getStudentAssignmentGrade(@PathVariable Integer studentID, @PathVariable Integer assignmentID) {
        GradeDTO gradeDTO = service.getStudentAssignmentGrade(studentID, assignmentID);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    @GetMapping("/student/{studentID}/assignment/{assignmentID}/Grade/Letter")
    public ResponseEntity<Character> getStudentAssignmentLetterGrade(@PathVariable Integer studentID, @PathVariable Integer assignmentID) {
        Character letterGrade = service.getStudentAssignmentLetterGrade(studentID, assignmentID);
        return (letterGrade == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(letterGrade, HttpStatus.OK);
    }


    // UPDATE
    @PutMapping(path = "/student", consumes = {"application/json"})
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto) {
        StudentDTO studentDTO = service.update(dto);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("student/{studentID}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer studentID) {
        StudentDTO deleted = service.getOne(studentID);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
