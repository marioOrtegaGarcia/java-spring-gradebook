package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @GetMapping("assignments")
    public ResponseEntity<List<AssignmentDTO>> getAssignments() {
        List<AssignmentDTO> assignments = service.findAll();
        return (assignments == null || assignments.size() == 0) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    // CREATE
    @PostMapping("assignment")
    public ResponseEntity<AssignmentDTO> addAssignment(@RequestBody AssignmentDTO assignment) {
        AssignmentDTO assignmentDTO = service.save(assignment);
        return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("assignment/{id}")
    public ResponseEntity<AssignmentDTO> getAssignment(@PathVariable Integer id) {
        AssignmentDTO findByIdOrElse = service.findById(id);
        return (findByIdOrElse == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(findByIdOrElse, HttpStatus.OK);
    }

    @GetMapping("assignmentByCourse/{id}")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentByCourseID(@PathVariable Integer id) {
        List<AssignmentDTO> assignmentsDTO = service.findAssignmentByCourseID(id);
        return (assignmentsDTO == null || assignmentsDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(assignmentsDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/assignment/{id}", consumes = {"application/json"})
    public ResponseEntity<AssignmentDTO> updateAssignment(@RequestBody AssignmentDTO dto, @PathVariable Integer id) {
        AssignmentDTO assignmentDTO = service.update(dto);
        return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/assignment/{id}")
    public ResponseEntity<AssignmentDTO> deleteAssignment(@PathVariable Integer id) {
        AssignmentDTO deleted = service.getOne(id);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
