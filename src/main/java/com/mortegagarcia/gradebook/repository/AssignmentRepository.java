package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Query("SELECT a " +
            "FROM Assignment a " +
            "WHERE a.course.id = :courseID")
    List<Assignment> findAssignmentByCourseID(@Param("courseID") Integer id);

    @Query("SELECT ca " +
            "FROM Course c, Student s " +
            "JOIN c.students cs " +
            "JOIN c.assignments ca " +
            "WHERE s.id = :id and s.id = cs.id ORDER BY ca.id")
    List<Assignment> findAssignmentsByStudentID(Integer id);

    @Query("SELECT AVG(g.score) as Average_Score " +
            "FROM Grade g " +
            "WHERE g.assignment.id = :assignmentID")
    Double getAssignmentAverageGrade(@Param("assignmentID") Integer assignmentID);

    @Query("SELECT MIN(g.score) as Minimum_Score " +
            "FROM Grade g " +
            "WHERE g.assignment.id = :assignmentID")
    Double getAssignmentMinimumGrade(@Param("assignmentID") Integer assignmentID);

    @Query("SELECT MAX(g.score) as Maximum_Score " +
            "FROM Grade g " +
            "WHERE g.assignment.id = :assignmentID")
    Double getAssignmentMaximumGrade(@Param("assignmentID") Integer assignmentID);

}
