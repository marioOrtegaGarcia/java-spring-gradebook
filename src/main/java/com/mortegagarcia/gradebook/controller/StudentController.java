package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.repository.StudentRepository;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Student> getStudents() {
        return repo.findAll();
    }

    // CREATE
    @PostMapping(path = "/student", consumes = { "application/json" })
    public StudentDTO addStudent(@RequestBody StudentDTO student) {
        repo.save(conv.dtoToEntity(student));
        return conv.entityToDTO(repo.getOne(student.getId()));
    }

    // READ
    @GetMapping("student/{id}")
    public StudentDTO getStudent(@PathVariable Integer id) {
        Student findByIdOrElse = repo.findById(id).orElse(null);
        return conv.entityToDTO(findByIdOrElse);
    }

    // UPDATE
    @PutMapping(path = "/student/{id}", consumes = { "application/json" })
    public StudentDTO updateStudent(@RequestBody StudentDTO dto, @PathVariable Integer id) {
        Student student = repo.findById(id).orElse(new Student());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGradeLevel(dto.getGradeLevel());
        student = repo.save(student);
        return conv.entityToDTO(student);
    }

    // DELETE
    @DeleteMapping("student/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        Student student = repo.getOne(id);
        repo.delete(student);
        return "deleted";
    }

}
