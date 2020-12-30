package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.repository.GradeRepository;

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
public class GradeController {

    @Autowired
    private GradeRepository repo;

    @Autowired
    private GradeConverter gConv;

    @Autowired
    private StudentConverter sConv;

    @Autowired
    private AssignmentConverter aConv;

    @GetMapping("grades")
    public ResponseEntity<List<GradeDTO>> getGrades() {
        List<Grade> findAll = repo.findAll();
        List<GradeDTO> gradesDTO = gConv.entityToDTO(findAll);
        return (gradesDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradesDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/grade", consumes = { "application/json" })
    public ResponseEntity<GradeDTO> addGrade(@RequestBody GradeDTO grade) {
        repo.save(gConv.dtoToEntity(grade));
        GradeDTO gradeDTO = gConv.entityToDTO(repo.getOne(grade.getId()));
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("grade/{id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Integer id) {
        Grade findByIdOrElse = repo.findById(id).orElse(null);
        GradeDTO gradeDTO = gConv.entityToDTO(findByIdOrElse);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}")
    public ResponseEntity<List<GradeDTO>> getGradeByAssignmentID(@PathVariable Integer id) {
        List<Grade> gradesByAssignmentID = repo.findGradeByAssignmentID(id);
        List<GradeDTO> gradesDTO = gConv.entityToDTO(gradesByAssignmentID);
        return (gradesDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}/avg")
    public ResponseEntity<Double> getAssignmentAverageScore(@PathVariable Integer id) {
        Double averageScore = repo.findAssignmentAverageScore(id);
        return (averageScore == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}/max")
    public ResponseEntity<Double> getAssignmentMaximumScore(@PathVariable Integer id) {
        Double averageScore = repo.findAssignmentMaximumScore(id);
        return (averageScore == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    @GetMapping(value = "grades/assignment/{id}/min")
    public ResponseEntity<Double> getAssignmentMinimumScore(@PathVariable Integer id) {
        Double averageScore = repo.findAssignmentMinimumScore(id);
        return (averageScore == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/grade/{id}", consumes = { "application/json" })
    public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO dto, @PathVariable Integer id) {
        Grade grade = repo.findById(id).orElse(new Grade());
        grade.setScore(dto.getScore());
        grade.setStudent(sConv.dtoToEntity(dto.getStudent()));
        grade.setAssignment(aConv.dtoToEntity(dto.getAssignment()));
        grade = repo.save(grade);
        GradeDTO gradeDTO = gConv.entityToDTO(grade);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/grade/{id}")
    public ResponseEntity<GradeDTO> deleteGrade(@PathVariable Integer id) {
        Grade grade = repo.getOne(id);
        GradeDTO deleted = gConv.entityToDTO(grade);
        if (deleted == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.delete(grade);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
