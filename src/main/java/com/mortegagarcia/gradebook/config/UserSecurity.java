package com.mortegagarcia.gradebook.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
	public boolean hasUserID(Authentication authentication, Integer userID) {
//        TODO: Implement a check to see if user has the id passed
		return true;
	}

	public boolean hasAccessToAssignmentWithID(Authentication authentication, Integer assignmentID) {
//        TODO: Implement a check to see if user has access to Assignment
		return true;
	}

	public boolean hasAccessToCourseWithID(Authentication authentication, Integer courseID) {
//        TODO: Implement a check to see if user has access to Course
		return true;
	}

	public boolean hasAccessToGradeWithID(Authentication authentication, Integer gradeID) {
//        TODO: Implement a check to see if user has access to Grade
		return true;
	}

	public boolean hasAccessToProfessorWithID(Authentication authentication, Integer professorID) {
//        TODO: Implement a check to see if user has access to Professor
		return true;
	}

	public boolean hasAccessToStudentWithID(Authentication authentication, Integer student) {
//        TODO: Implement a check to see if user has access to Student
		return true;
	}
}