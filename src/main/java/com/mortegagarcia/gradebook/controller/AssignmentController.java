package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @GetMapping("assignment/all")
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
    @GetMapping("assignment/{assignmentID}")
    public ResponseEntity<AssignmentDTO> getAssignment(@PathVariable Integer assignmentID) {
        AssignmentDTO findByIdOrElse = service.findById(assignmentID);
        return (findByIdOrElse == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(findByIdOrElse, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/assignment/", consumes = {"application/json"})
    public ResponseEntity<AssignmentDTO> updateAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        assignmentDTO = service.update(assignmentDTO);
        return (assignmentDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/assignment/{assignmentID}")
    public ResponseEntity<AssignmentDTO> deleteAssignment(@PathVariable Integer assignmentID) {
        AssignmentDTO deleted = service.getOne(assignmentID);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("assignment/{assignmentID}/grade/min")
    public ResponseEntity<Double> getAssignmentMinimumGrade(@PathVariable Integer assignmentID) {
        Double minimumAssignmentGrade = service.getAssignmentMinimumGrade(assignmentID);
        return (minimumAssignmentGrade == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(minimumAssignmentGrade, HttpStatus.OK);
    }

    @GetMapping("assignment/{assignmentID}/grade/avg")
    public ResponseEntity<Double> getAssignmentAverageGrade(@PathVariable Integer assignmentID) {
        Double minimumAssignmentGrade = service.getAssignmentAverageGrade(assignmentID);
        return (minimumAssignmentGrade == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(minimumAssignmentGrade, HttpStatus.OK);
    }

    @GetMapping("assignment/{assignmentID}/grade/max")
    public ResponseEntity<Double> getAssignmentMaximumGrade(@PathVariable Integer assignmentID) {
        Double minimumAssignmentGrade = service.getAssignmentMaximumGrade(assignmentID);
        return (minimumAssignmentGrade == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(minimumAssignmentGrade, HttpStatus.OK);
    }
}
