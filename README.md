# Gradebook

This Gradebook API was created using the Java Spring Boot API.

## Features

## API Map

- api/
    - assignment/
        - /
            - POST - Create new Assignment
            - PUT - Update existing Assignment
        - /{assignmentID}/
            - GET - Get Assignment by ID
            - DELETE - Delete Assignment by ID
            - /Grade/
                - /Min/
                    - GET - Get minimum score for assignment
                - /Avg/
                    - GET - Get average score for assignment
                - /Max/
                    - GET - Get maximum score for assignment
        - /All/
            - GET - Get all Assignment
    - course/
        - /
            - POST - Create new Course
            - PUT - Update existing Course
        - /{courseID}/
            - GET - Get Course by ID
            - DELETE - Delete Course by ID
            - assignment/
                - {ALL}
                    - GET - Get all assignments by course
                    - DELETE - Delete all assignments by course
                - {assignmentID}
                    - GET - Get assignment from course
        - /All/
            - GET - Get all Courses
            - /professor/
                - /null/
                    - GET - Get all courses where professor id is null
                - /{professorID}/
                    - GET - Get all courses from professor with id
    - grade/
        - /
            - POST - Create new Grade
            - PUT  - Update existing Grade
        - /{gradeID}/
            - GET - Get grade by ID
            - DELETE - Delete Grade by ID
        - /All/
            - GET - Get all grades
        
    - professor/
        - /
            - POST - Create new professor
            - PUT - Update existing professor
        - /{professorID}/
            - GET - Get professor by id
            - DELETE - Delete professor by id
        - /All/
            - GET - Get all professors 
    - student/
        - /
            - POST - Create new student
            - PUT - Update existing student
        - /{studentID}/
            - GET - Get student by id
            - DELETE - Delete student by id
            - /assignment/
                - {assignmentID}
                    - /Grade/
                        - /
                            - GET - Get grade for the student for assignment
                        - /Letter
                            - GET - Get letter grade for the student for assignment
        - /all/
            - GET - Get all students
            
## Features & Bug Fixes to Implement

### Services
- [ ] Clean Services and make methods more efficient with SLQ calls

### Controllers

- [x] Move over to returning ResponseEntity objects for controllers
- [x] Find Courses without professors
- [x] Handle errors in DTO & Controller when nothing is returned, handle the null or throw an error.
- [x] Get Average, Minimum, and Maximum grade for assignment.
- [x] Should convert entities into DTO inside of service not controller.
- [X] Compute a course letter grade.
- [ ] Find students missing grades for an assignment.

### Hibernate

- [x] Fix removing Professors - Remove Professor only, remember to clear the professor id of any courses they have
  taught
- [x] Fix removing Students - Remove Student only, remember to clear the student id of any grades the student has been
  graded for
- [x] Fix removing Courses - Remove Course & Assignment attached to this course
- [x] Fix removing Assignments - Remove Assignment & Grade attached to this course
- [x] Fix removing Grades - Remove Grades only

### Aspect Oriented Programming

- [ ] Create Integration, Controller and Service Logs
    - [X] Professor
    - [X] Student
    - [X] Course
    - [X] Assignment
    - [X] Grade
