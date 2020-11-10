package com.mortegagarcia.gradebook.repository;

import java.util.List;

import com.mortegagarcia.gradebook.model.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseID")
    List<Assignment> findAssignmentByCourseID(@Param("courseID") Integer id);

}
