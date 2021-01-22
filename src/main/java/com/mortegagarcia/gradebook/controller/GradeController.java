package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {

    @Autowired
    private GradeService service;

    @GetMapping("grades")
    public ResponseEntity<List<GradeDTO>> getGrades() {
        List<GradeDTO> gradesDTO = service.findAll();
        return (gradesDTO == null || gradesDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradesDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/grade", consumes = {"application/json"})
    public ResponseEntity<GradeDTO> addGrade(@RequestBody GradeDTO grade) {
        GradeDTO gradeDTO = service.save(grade);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("grade/{id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Integer id) {
        GradeDTO gradeDTO = service.findById(id);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}")
    public ResponseEntity<List<GradeDTO>> getGradeByAssignmentID(@PathVariable Integer id) {
        List<GradeDTO> gradesDTO = service.findGradeByAssignmentID(id);
        return (gradesDTO == null || gradesDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}/avg")
    public ResponseEntity<Double> getAssignmentAverageScore(@PathVariable Integer id) {
        Double averageScore = service.findAssignmentAverageScore(id);
        return (averageScore == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}/max")
    public ResponseEntity<Double> getAssignmentMaximumScore(@PathVariable Integer id) {
        Double averageScore = service.findAssignmentMaximumScore(id);
        return (averageScore == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}/min")
    public ResponseEntity<Double> getAssignmentMinimumScore(@PathVariable Integer id) {
        Double averageScore = service.findAssignmentMinimumScore(id);
        return (averageScore == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/grade/{id}", consumes = {"application/json"})
    public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO dto, @PathVariable Integer id) {
        GradeDTO gradeDTO = service.update(dto);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/grade/{id}")
    public ResponseEntity<GradeDTO> deleteGrade(@PathVariable Integer id) {
        GradeDTO deleted = service.getOne(id);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
