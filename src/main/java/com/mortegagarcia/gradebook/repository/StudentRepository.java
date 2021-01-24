package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentID and g.assignment.id = :assignmentID")
    Grade getStudentAssignmentGrade(Integer studentID, Integer assignmentID);

    @Query("SELECT CASE\n" +
            "           WHEN (g.score / a.possibleScore) * 100 >= 90 THEN 'A'\n" +
            "           WHEN (g.score / a.possibleScore) * 100 >= 80 THEN 'B'\n" +
            "           WHEN (g.score / a.possibleScore) * 100 >= 70 THEN 'C'\n" +
            "           WHEN (g.score / a.possibleScore) * 100 >= 60 THEN 'D'\n" +
            "           WHEN (g.score / a.possibleScore) * 100 < 60 THEN 'F'\n" +
            "           ELSE NULL\n" +
            "           END AS Letter_Grade\n" +
            "FROM Assignment a,\n" +
            "     Grade g\n" +
            "WHERE a.id = g.assignment.id\n" +
            "  and a.id = :assignmentID\n" +
            "  and g.student.id = :studentID")
    Character getStudentAssignmentLetterGrade(Integer studentID, Integer assignmentID);

}
