package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("courses")
    public ResponseEntity<List<CourseDTO>> getCourses() {
        List<CourseDTO> coursesDTO = service.findAll();
        return (coursesDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    // CREATE
    @PostMapping(path = "/course", consumes = {"application/json"})
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) {
        CourseDTO courseDTO = service.save(course);
        return (courseDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("course/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Integer id) {
        CourseDTO course = service.findById(id);
        return (course == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("courses/professor/{id}")
    public ResponseEntity<List<CourseDTO>> findCourseByProfessorID(@PathVariable Integer id) {
        List<CourseDTO> coursesDTO = service.findCoursesByProfessorID(id);
        return getListResponseEntity(coursesDTO);
    }

    @GetMapping("courses/professor/null")
    public ResponseEntity<List<CourseDTO>> findCourseWhereProfessorIsNull() {
        List<CourseDTO> coursesDTO = service.findCoursesWhereProfessorIsNull();
        return getListResponseEntity(coursesDTO);
    }

    // UPDATE
    @PutMapping(path = "/course/{id}", consumes = {"application/json"})
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO dto, @PathVariable Integer id) {
        CourseDTO courseDTO = service.update(dto);
        return (courseDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/course/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Integer id) {
        CourseDTO deleted = service.getOne(id);
        if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    private ResponseEntity<List<CourseDTO>> getListResponseEntity(List<CourseDTO> coursesDTO) {
        if (coursesDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (coursesDTO.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

}
