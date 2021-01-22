package com.mortegagarcia.gradebook.controller;

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


    @GetMapping("students")
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
    @GetMapping("student/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id) {
        StudentDTO studentDTO = service.findById(id);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/student/{id}", consumes = {"application/json"})
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Integer id) {
        StudentDTO studentDTO = service.update(dto);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("student/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer id) {
        StudentDTO deleted = service.getOne(id);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
