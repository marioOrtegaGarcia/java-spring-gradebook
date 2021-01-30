package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("SELECT g FROM Grade g WHERE g.student.id = :studentID and g.assignment.id = :assignmentID")
	Grade getStudentAssignmentGrade(Integer studentID, Integer assignmentID);

	@Query("SELECT CASE WHEN (g.score / a.possibleScore) * 100 >= 90 THEN 'A' " +
			"           WHEN (g.score / a.possibleScore) * 100 >= 80 THEN 'B' " +
			"           WHEN (g.score / a.possibleScore) * 100 >= 70 THEN 'C' " +
			"           WHEN (g.score / a.possibleScore) * 100 >= 60 THEN 'D' " +
			"           WHEN (g.score / a.possibleScore) * 100 < 60 THEN 'F' " +
			"           ELSE NULL " +
			"           END AS Letter_Grade " +
			"FROM Assignment a, " +
			"     Grade g " +
			"WHERE a.id = g.assignment.id " +
			"  and a.id = :assignmentID " +
			"  and g.student.id = :studentID")
	Character getStudentAssignmentLetterGrade(Integer studentID, Integer assignmentID);

	@Query("SELECT DISTINCT ca " +
			"FROM Course c, Student s, Grade g " +
			"JOIN c.students cs " +
			"JOIN c.assignments ca " +
			"JOIN g.assignment ga " +
			"WHERE s.id = :studentID and s.id = cs.id and ga.id <> ca.id " +
			"ORDER BY ca.id asc")
	Optional<List<Assignment>> findAllByIdAndMissingAssignments(Integer studentID);

}
