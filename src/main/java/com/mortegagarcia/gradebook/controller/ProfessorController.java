package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.ProfessorConverter;
import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.model.Professor;
import com.mortegagarcia.gradebook.repository.ProfessorRepository;

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
public class ProfessorController {

    @Autowired
    private ProfessorRepository repo;

    @Autowired
    private ProfessorConverter conv;

    @GetMapping("professors")
    public ResponseEntity<List<ProfessorDTO>> getProfessors() {
        List<Professor> findAll = repo.findAll();
        List<ProfessorDTO> professorsDTO = conv.entityToDTO(findAll);
        return (professorsDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(professorsDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/professor", consumes = { "application/json" })
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody ProfessorDTO professor) {
        Professor saved = repo.save(conv.dtoToEntity(professor));
        ProfessorDTO professorDTO = conv.entityToDTO(repo.getOne(saved.getId()));
        return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("professor/{id}")
    public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Integer id) {
        Professor findByIdOrElse = repo.findById(id).orElse(null);
        ProfessorDTO professorDTO = conv.entityToDTO(findByIdOrElse);
        return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/professor/{id}", consumes = { "application/json" })
    public ResponseEntity<ProfessorDTO> updateProfessor(@RequestBody ProfessorDTO dto, @PathVariable Integer id) {
        Professor professor = repo.findById(id).orElse(new Professor());
        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setEmail(dto.getEmail());
        professor.setPhoneNumber(dto.getPhoneNumber());
        professor = repo.save(professor);
        ProfessorDTO professorDTO = conv.entityToDTO(professor);
        return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/professor/{id}")
    public ResponseEntity<ProfessorDTO> deleteProfessor(@PathVariable Integer id) {
        Professor professor = repo.getOne(id);
        ProfessorDTO deleted = conv.entityToDTO(professor);
        if (deleted == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Course> courses = professor.getCourses();
        for (Course c : courses)
            c.setProfessor(null);
        repo.delete(professor);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
