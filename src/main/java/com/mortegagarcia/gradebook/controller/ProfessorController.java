package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.ProfessorConverter;
import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.model.Professor;
import com.mortegagarcia.gradebook.repository.ProfessorRepository;

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
public class ProfessorController {

    @Autowired
    private ProfessorRepository repo;

    @Autowired
    private ProfessorConverter conv;

    @GetMapping("professors")
    public List<ProfessorDTO> getProfessors() {
        List<Professor> findAll = repo.findAll();
        return conv.entityToDTO(findAll);
    }

    // CREATE
    @PostMapping(path = "/professor", consumes = { "application/json" })
    public ProfessorDTO addProfessor(@RequestBody ProfessorDTO professor) {
        repo.save(conv.dtoToEntity(professor));
        return conv.entityToDTO(repo.getOne(professor.getId()));
    }

    // READ
    @GetMapping("professor/{id}")
    public ProfessorDTO getProfessor(@PathVariable Integer id) {
        Professor findByIdOrElse = repo.findById(id).orElse(null);
        // TODO: Throw HTML error for wrong id
        return conv.entityToDTO(findByIdOrElse);
    }

    // UPDATE
    @PutMapping(path = "/professor/{id}", consumes = { "application/json" })
    public ProfessorDTO updateProfessor(@RequestBody ProfessorDTO dto, @PathVariable Integer id) {
        Professor professor = repo.findById(id).orElse(new Professor());
        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setEmail(dto.getEmail());
        professor = repo.save(professor);
        return conv.entityToDTO(professor);
    }

    // DELETE
    @DeleteMapping("/professor/{id}")
    public ProfessorDTO deleteProfessor(@PathVariable Integer id) {
        Professor professor = repo.getOne(id);
        ProfessorDTO deleted = conv.entityToDTO(professor);
        repo.delete(professor);
        return deleted;
    }

}
