# Gradebook

This Gradebook API was created using the Java Spring Boot API.

## Bugs

- [x] Fix removing Professors - Remove Professor only, rememeber to clear the professor id of any courses they have taught
- [x] Fix removing Students - Remove Student only, rememeber to clear the student id of any grades the student has been gradded for
- [x] Fix removing Courses - Remove Course & Assignment attached to this course
- [x] Fix removing Assignments - Remove Assignment & Grade attached to this course
- [x] Fix removing Grades - Remove Grades only
- [ ] Handle errors in DTO & Controller when nothing is returned, handle the null or throw an error.

## Features to implement

- [ ] Move over to returning ResponseEntity objects for controllers
- [x] Find Courses without professors
- [ ] Find students missing grades for an assignment.
- [ ] Get Average, Minimum, and Maximum grade for assignment.
- [ ] Compute a course letter grade.

### Loggs Cases

- [ ] Create Integration, Controller and Service Loggs
  - [ ] Professor
  - [ ] Student
  - [ ] Course
  - [ ] Assignment
  - [ ] Grade

### Feature Backlog
