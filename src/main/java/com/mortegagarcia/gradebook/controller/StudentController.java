package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.repository.StudentRepository;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @Autowired
    private StudentConverter conv;

    @GetMapping("students")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<Student> students = repo.findAll();
        List<StudentDTO> studentDTO = conv.entityToDTO(students);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/student", consumes = { "application/json" })
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student) {
        repo.save(conv.dtoToEntity(student));
        StudentDTO studentDTO = conv.entityToDTO(repo.getOne(student.getId()));
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("student/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id) {
        Student findByIdOrElse = repo.findById(id).orElse(null);
        StudentDTO studentDTO = conv.entityToDTO(findByIdOrElse);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/student/{id}", consumes = { "application/json" })
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Integer id) {
        Student student = repo.findById(id).orElse(new Student());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGradeLevel(dto.getGradeLevel());
        student.setEmail(dto.getEmail());
        student = repo.save(student);
        StudentDTO studentDTO = conv.entityToDTO(student);
        return (studentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("student/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer id) {
        Student student = repo.getOne(id);
        StudentDTO deleted = conv.entityToDTO(student);
        if (deleted == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.delete(student);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
