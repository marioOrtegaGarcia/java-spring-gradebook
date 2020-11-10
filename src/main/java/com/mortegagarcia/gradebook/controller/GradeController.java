package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.repository.GradeRepository;

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
    public List<Grade> getGrades() {
        return repo.findAll();
    }

    // CREATE
    @PostMapping(path = "/grade", consumes = { "application/json" })
    public GradeDTO addGrade(@RequestBody GradeDTO grade) {
        repo.save(gConv.dtoToEntity(grade));
        return gConv.entityToDTO(repo.getOne(grade.getId()));
    }

    // READ
    @GetMapping("grade/{id}")
    public GradeDTO getGrade(@PathVariable Integer id) {
        Grade findByIdOrElse = repo.findById(id).orElse(null);
        return gConv.entityToDTO(findByIdOrElse);
    }

    @GetMapping(value = "grades/assignment/{id}")
    public List<GradeDTO> getGradeByAssignmentID(@PathVariable Integer id) {
        return gConv.entityToDTO(repo.findGradeByAssignmentID(id));
    }

    @GetMapping(value = "grades/avg/assignment/{id}")
    public Double getAssignmentAverageScore(@PathVariable Integer id) {
        return repo.findAssignmentAverageScore(id);
    }

    // UPDATE
    @PutMapping(path = "/grade/{id}", consumes = { "application/json" })
    public GradeDTO updateGrade(@RequestBody GradeDTO dto, @PathVariable Integer id) {
        Grade grade = repo.findById(id).orElse(new Grade());
        grade.setId(id);
        grade.setScore(dto.getScore());
        grade.setStudent(sConv.dtoToEntity(dto.getStudent()));
        grade.setAssignment(aConv.dtoToEntity(dto.getAssignment()));
        grade = repo.save(grade);
        return gConv.entityToDTO(grade);
    }

    // DELETE
    @DeleteMapping("/grade/{id}")
    public GradeDTO deleteGrade(@PathVariable Integer id) {
        Grade grade = repo.getOne(id);
        GradeDTO deleted = gConv.entityToDTO(grade);
        repo.delete(grade);
        return deleted;
    }

}
