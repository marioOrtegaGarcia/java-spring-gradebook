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

    @GetMapping("grade/all")
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
    @GetMapping("grade/{gradeID}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Integer gradeID) {
        GradeDTO gradeDTO = service.findById(gradeID);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/grade/{{gradeID}}", consumes = {"application/json"})
    public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO dto, @PathVariable Integer gradeID) {
        GradeDTO gradeDTO = service.update(gradeID, dto);
        return (gradeDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/grade/{gradeID}")
    public ResponseEntity<GradeDTO> deleteGrade(@PathVariable Integer gradeID) {
        GradeDTO deleted = service.getOne(gradeID);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
