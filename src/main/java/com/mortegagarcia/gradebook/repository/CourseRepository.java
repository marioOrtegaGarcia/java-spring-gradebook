package com.mortegagarcia.gradebook.repository;

import java.util.List;

import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.professor.id = :professorID")
    List<Course> findCoursesByProfessorID(@Param("professorID") Integer id);

    @Query("SELECT c FROM Course c WHERE c.professor = NULL")
    List<Course> findCoursesWhereProfessorIsNull();

    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseID and a.id = :assignmentID")
    Assignment findCourseAssignment(@Param("courseID") Integer courseID, @Param("assignmentID") Integer assignmentID);

    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseID")
    List<Assignment> getCourseAssignments(@Param("courseID") Integer courseID);

    @Transactional
    @Modifying
    @Query("DELETE FROM Assignment a WHERE a.course.id = :courseID")
    void deleteCourseAssignments(Integer courseID);
}
