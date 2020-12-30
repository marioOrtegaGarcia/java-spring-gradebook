# Gradebook

This Gradebook API was created using the Java Spring Boot API.

## Features

## Features & Bug Fixes to Implement

### Controllers

- [x] Move over to returning ResponseEntity objects for controllers
- [x] Find Courses without professors
- [x] Handle errors in DTO & Controller when nothing is returned, handle the null or throw an error.
- [x] Get Average, Minimum, and Maximum grade for assignment.
- [ ] Compute a course letter grade.
- [ ] Find students missing grades for an assignment.

### Hibernate

- [x] Fix removing Professors - Remove Professor only, rememeber to clear the professor id of any courses they have taught
- [x] Fix removing Students - Remove Student only, rememeber to clear the student id of any grades the student has been gradded for
- [x] Fix removing Courses - Remove Course & Assignment attached to this course
- [x] Fix removing Assignments - Remove Assignment & Grade attached to this course
- [x] Fix removing Grades - Remove Grades only

### Aspect Oriented Programming

- [ ] Create Integration, Controller and Service Loggs
  - [ ] Professor
  - [ ] Student
  - [ ] Course
  - [ ] Assignment
  - [ ] Grade
