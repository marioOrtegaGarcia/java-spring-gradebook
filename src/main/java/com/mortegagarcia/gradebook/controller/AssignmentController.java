package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;

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
public class AssignmentController {

    @Autowired
    private AssignmentRepository repo;

    @Autowired
    private AssignmentConverter conv;

    @GetMapping("assignments")
    public ResponseEntity<List<AssignmentDTO>> getAssignments() {
        List<Assignment> findAll = repo.findAll();
        List<AssignmentDTO> assignmentsDTO = conv.entityToDTO(findAll);
        return (assignmentsDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(assignmentsDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping("assignment")
    public ResponseEntity<AssignmentDTO> addAssignment(@RequestBody AssignmentDTO assignment) {
        repo.save(conv.dtoToEntity(assignment));
        AssignmentDTO assignmentDTO = conv.entityToDTO(repo.getOne(assignment.getId()));
        return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("assignment/{id}")
    public ResponseEntity<AssignmentDTO> getAssignment(@PathVariable Integer id) {
        Assignment findByIdOrElse = repo.findById(id).orElse(null);
        AssignmentDTO assignmentDTO = conv.entityToDTO(findByIdOrElse);
        return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
    }

    @GetMapping("assignmentByCourse/{id}")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentByCourseID(@PathVariable Integer id) {
        List<Assignment> assignmentsByCourseID = repo.findAssignmentByCourseID(id);
        List<AssignmentDTO> assignmentsDTO = conv.entityToDTO(assignmentsByCourseID);
        return (assignmentsDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(assignmentsDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/assignment/{id}", consumes = { "application/json" })
    public ResponseEntity<AssignmentDTO> updateAssignment(@RequestBody AssignmentDTO dto, @PathVariable Integer id) {
        Assignment assignment = repo.findById(id).orElse(new Assignment());
        assignment.setId(id);
        assignment.setName(dto.getName());
        assignment.setPossibleScore(dto.getPossibleScore());
        assignment = repo.save(assignment);
        AssignmentDTO assignmentDTO = conv.entityToDTO(assignment);
        return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/assignment/{id}")
    public ResponseEntity<AssignmentDTO> deleteAssignment(@PathVariable Integer id) {
        Assignment assignment = repo.getOne(id);
        AssignmentDTO deleted = conv.entityToDTO(assignment);
        if (deleted == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.delete(assignment);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
