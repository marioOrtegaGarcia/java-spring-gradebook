package com.mortegagarcia.gradebook.repository;

import java.util.List;

import com.mortegagarcia.gradebook.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.professor.id = :professorID")
    List<Course> findCourseByProfessorID(@Param("professorID") Integer id);
}
