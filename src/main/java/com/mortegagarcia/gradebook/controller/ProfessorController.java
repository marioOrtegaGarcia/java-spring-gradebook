package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping("professors")
    public ResponseEntity<List<ProfessorDTO>> getProfessors() {
        List<ProfessorDTO> professorsDTO = service.findAll();
        return (professorsDTO == null || professorsDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(professorsDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/professor", consumes = {"application/json"})
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody ProfessorDTO professor) {
        ProfessorDTO professorDTO = service.save(professor);
        return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("professor/{id}")
    public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Integer id) {
        ProfessorDTO professorDTO = service.findById(id);
        return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/professor/{id}", consumes = {"application/json"})
    public ResponseEntity<ProfessorDTO> updateProfessor(@RequestBody ProfessorDTO dto, @PathVariable Integer id) {
        ProfessorDTO professorDTO = service.update(dto);
        return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/professor/{id}")
    public ResponseEntity<ProfessorDTO> deleteProfessor(@PathVariable Integer id) {
        ProfessorDTO deleted = service.getOne(id);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.deleteCoursesByProfessor(deleted);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
