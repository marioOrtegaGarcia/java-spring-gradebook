# Gradebook

This Gradebook API was created using the Java Spring Boot API.

## Features

## Features & Bug Fixes to Implement

### Controllers

- [x] Move over to returning ResponseEntity objects for controllers
- [x] Find Courses without professors
- [x] Handle errors in DTO & Controller when nothing is returned, handle the null or throw an error.
- [x] Get Average, Minimum, and Maximum grade for assignment.
- [ ] Should convert entities into DTO inside of service not controller.
- [ ] Compute a course letter grade.
- [ ] Find students missing grades for an assignment.

### Hibernate

- [x] Fix removing Professors - Remove Professor only, remember to clear the professor id of any courses they have taught
- [x] Fix removing Students - Remove Student only, remember to clear the student id of any grades the student has been graded for
- [x] Fix removing Courses - Remove Course & Assignment attached to this course
- [x] Fix removing Assignments - Remove Assignment & Grade attached to this course
- [x] Fix removing Grades - Remove Grades only

### Aspect Oriented Programming

- [ ] Create Integration, Controller and Service Logs
  - [ ] Professor
  - [ ] Student
  - [ ] Course
  - [ ] Assignment
  - [ ] Grade
