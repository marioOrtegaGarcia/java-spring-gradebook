package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;

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
public class AssignmentController {

    @Autowired
    private AssignmentRepository repo;

    @Autowired
    private AssignmentConverter conv;

    @GetMapping("assignments")
    public List<Assignment> getAssignments() {
        return repo.findAll();
    }

    // CREATE
    @PostMapping("assignment")
    public AssignmentDTO addAssignment(@RequestBody AssignmentDTO assignment) {
        repo.save(conv.dtoToEntity(assignment));
        return conv.entityToDTO(repo.getOne(assignment.getId()));
    }

    // READ
    @GetMapping("assignment/{id}")
    public AssignmentDTO getAssignment(@PathVariable Integer id) {
        Assignment findByIdOrElse = repo.findById(id).orElse(null);
        return conv.entityToDTO(findByIdOrElse);
    }

    @GetMapping("assignmentByCourse/{id}")
    public List<Assignment> getAssignmentByCourseID(@PathVariable Integer id) {
        return repo.findAssignmentByCourseID(id);
    }

    // UPDATE
    @PutMapping(path = "/assignment/{id}", consumes = { "application/json" })
    public AssignmentDTO updateAssignment(@RequestBody AssignmentDTO dto, @PathVariable Integer id) {
        Assignment assignment = repo.findById(id).orElse(new Assignment());
        assignment.setId(id);
        assignment.setName(dto.getName());
        assignment.setPossibleScore(dto.getPossibleScore());
        assignment = repo.save(assignment);
        return conv.entityToDTO(assignment);
    }

    // DELETE
    @DeleteMapping("/assignment/{id}")
    public AssignmentDTO deleteAssignment(@PathVariable Integer id) {
        Assignment assignment = repo.getOne(id);
        AssignmentDTO deleted = conv.entityToDTO(assignment);
        repo.delete(assignment);
        return deleted;
    }

}
