package com.mortegagarcia.gradebook.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

	public boolean hasAccessToAssignmentWithID(Authentication authentication, Integer assignmentID) {
//        TODO: Implement a check to see if user has access to Assignment
		System.out.println(authentication.getAuthorities());
		return true;
	}

	public boolean hasAccessToCourseWithID(Authentication authentication, Integer courseID) {
//        TODO: Implement a check to see if user has access to Course
//		System.out.println(authentication.getAuthorities());
		return true;
	}

	public boolean hasAccessToGradeWithID(Authentication authentication, Integer gradeID) {
//        TODO: Implement a check to see if user has access to Grade
//		System.out.println(authentication.getAuthorities());
		return true;
	}

	public boolean hasAccessToProfessorWithID(Authentication authentication, Integer professorID) {
//        TODO: Implement a check to see if user has access to Professor
//		System.out.println(authentication.getAuthorities());
		return true;
	}

	public boolean hasAccessToStudentWithID(Authentication authentication, Integer student) {
//        TODO: Implement a check to see if user has access to Student
//		System.out.println(authentication.getAuthorities());
		return true;
	}
}