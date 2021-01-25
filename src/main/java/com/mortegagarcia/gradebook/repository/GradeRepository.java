package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query("SELECT g FROM Grade g WHERE g.assignment.id = :assignmentID")
    List<Grade> findGradeByAssignmentID(@Param("assignmentID") Integer id);

}
