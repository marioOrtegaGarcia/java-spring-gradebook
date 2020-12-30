package com.mortegagarcia.gradebook.repository;

import java.util.List;

import com.mortegagarcia.gradebook.model.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query("SELECT g FROM Grade g WHERE g.assignment.id = :assignmentID")
    List<Grade> findGradeByAssignmentID(@Param("assignmentID") Integer id);

    @Query("SELECT AVG(CAST(g.score as double)) FROM Grade g WHERE g.assignment.id = :assignmentID")
    Double findAssignmentAverageScore(@Param("assignmentID") Integer id);

    @Query("SELECT MIN(CAST(g.score as double)) FROM Grade g WHERE g.assignment.id = :assignmentID")
    Double findAssignmentMinimumScore(@Param("assignmentID") Integer id);

    @Query("SELECT MAX(CAST(g.score as double)) FROM Grade g WHERE g.assignment.id = :assignmentID")
    Double findAssignmentMaximumScore(@Param("assignmentID") Integer id);
}
