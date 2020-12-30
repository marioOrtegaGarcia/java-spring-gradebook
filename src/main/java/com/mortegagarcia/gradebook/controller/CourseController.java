package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.CourseConverter;
import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.CourseRepository;

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
public class CourseController {

    @Autowired
    private CourseRepository repo;

    @Autowired
    private CourseConverter conv;

    @GetMapping("courses")
    public ResponseEntity<List<CourseDTO>> getCourses() {
        List<Course> findAll = repo.findAll();
        List<CourseDTO> coursesDTO = conv.entityToDTO(findAll);
        return (coursesDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(coursesDTO, HttpStatus.OK);

    }

    // CREATE
    @PostMapping(path = "/course", consumes = { "application/json" })
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) {
        repo.save(conv.dtoToEntity(course));
        CourseDTO courseDTO = conv.entityToDTO(repo.getOne(course.getId()));
        return (courseDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    // READ
    @GetMapping("course/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Integer id) {
        Course findByIdOrElse = repo.findById(id).orElse(null);
        CourseDTO course = conv.entityToDTO(findByIdOrElse);
        if (course == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("courses/professor/{id}")
    public ResponseEntity<List<CourseDTO>> findCourseByProffessorID(@PathVariable Integer id) {
        List<Course> courses = repo.findCourseByProfessorID(id);
        List<CourseDTO> coursesDTO = conv.entityToDTO(courses);
        if (coursesDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping("courses/professor/null")
    public ResponseEntity<List<CourseDTO>> findCourseWhereProffessorIsNull() {
        List<Course> courses = repo.findCourseWhereProfessorIsNull();
        List<CourseDTO> coursesDTO = conv.entityToDTO(courses);
        if (coursesDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping(path = "/course/{id}", consumes = { "application/json" })
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO dto, @PathVariable Integer id) {
        Course course = repo.findById(id).orElse(new Course());
        course.setId(id);
        course.setName(dto.getName());
        course = repo.save(course);
        CourseDTO courseDTO = conv.entityToDTO(course);
        return (courseDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/course/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Integer id) {
        Course course = repo.getOne(id);
        CourseDTO deleted = conv.entityToDTO(course);
        repo.delete(course);
        return (deleted == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
