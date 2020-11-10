package com.mortegagarcia.gradebook.controller;

import java.util.List;

import com.mortegagarcia.gradebook.converter.CourseConverter;
import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.CourseRepository;

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
public class CourseController {

    @Autowired
    private CourseRepository repo;

    @Autowired
    private CourseConverter conv;

    @GetMapping("courses")
    public List<Course> getCourses() {
        return repo.findAll();
    }

    // CREATE
    @PostMapping(path = "/course", consumes = { "application/json" })
    public CourseDTO addCourse(@RequestBody CourseDTO course) {
        repo.save(conv.dtoToEntity(course));
        return conv.entityToDTO(repo.getOne(course.getId()));
    }

    // READ
    @GetMapping("course/{id}")
    public CourseDTO getCourse(@PathVariable Integer id) {
        Course findByIdOrElse = repo.findById(id).orElse(null);
        // TODO: Throw HTML error for wrong id
        return conv.entityToDTO(findByIdOrElse);
    }

    @GetMapping("courses/professor/{id}")
    public List<Course> findCourseByProffessorID(@PathVariable Integer id) {
        return repo.findCourseByProfessorID(id);
    }

    // UPDATE
    @PutMapping(path = "/course/{id}", consumes = { "application/json" })
    public CourseDTO updateCourse(@RequestBody CourseDTO dto, @PathVariable Integer id) {
        Course course = repo.findById(id).orElse(new Course());
        course.setId(id);
        course.setName(dto.getName());
        course = repo.save(course);
        return conv.entityToDTO(course);
    }

    // DELETE
    @DeleteMapping("/course/{id}")
    public CourseDTO deleteCourse(@PathVariable Integer id) {
        Course course = repo.getOne(id);
        CourseDTO deleted = conv.entityToDTO(course);
        // TODO: Handle delete conflicts, issues with cascade type causing deletion of
        // students. Fix by creating custom delete function
        repo.delete(course);
        return deleted;
    }

}
