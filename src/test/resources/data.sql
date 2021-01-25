INSERT INTO PROFESSOR
VALUES (1, 22, 'mario@gmail.com', 'Mario', 'Mario', '5105555555'),
       (2, 23, 'luigi@gmail.com', 'Luigi', 'Mario', '5105551234'),
       (3, 24, 'toad@gmail.com', 'Toad', 'Mushroom', '5105554321');

INSERT INTO COURSE
VALUES (1, 'Linear Algebra'),
       (2, 'Machine Learning'),
       (3, 'Computer Vision');

INSERT INTO COURSE_PROFESSOR
VALUES (1, 1),
       (2, 1),
       (3, 3);

INSERT INTO STUDENT
VALUES (1, 12, 'Bowser', 'Turtle', 'bowser@gmail.com'),
       (2, 12, 'Turtle', 'Turtle', 'turtle@gmail.com'),
       (3, 12, 'Yoshi', 'Dinosaur', 'yoshi@gmail.com'),
       (4, 12, 'Wario', 'Wario', 'wario@gmail.com'),
       (5, 12, 'Waluigi', 'Wario', 'waluigi@gmail.com'),
       (6, 12, 'Rosalina', 'Person', 'rosalia@gmail.com'),
       (7, 12, 'Peach', 'Princess', 'peach@gmail.com'),
       (8, 12, 'Daisy', 'Princess', 'daidy@gmail.com'),
       (9, 12, 'Kamek', 'Witch', 'kamek@gmail.com'),
       (10, 12, 'Birdo', 'Dinosaur', 'birdo@gmail.com'),
       (11, 12, 'Chain Chomp', 'Creature', 'chainchomp@gmail.com');

INSERT INTO COURSE_STUDENT
VALUES (1, 1),
       (1, 3),
       (1, 6),
       (1, 7),
       (1, 10),
       (2, 1),
       (2, 2),
       (2, 4),
       (2, 5),
       (2, 9),
       (3, 2),
       (3, 3),
       (3, 5),
       (3, 6),
       (3, 10);

INSERT INTO ASSIGNMENT
VALUES (1, 'Kernel Filter', 10, 3),
       (4, 'Pyramid Image Processing', 10, 3),
       (2, 'Properties of a Linear Function', 10, 1),
       (3, 'Dimensionality Reduction', 10, 2);

INSERT INTO GRADE
VALUES (1, 8, 1, 2),
       (2, 4, 1, 3),
       (3, 7, 1, 5),
       (4, 8, 1, 6),
       (5, 10, 1, 10);