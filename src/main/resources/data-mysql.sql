INSERT INTO EMAIL (id, email)
VALUES (1, 'mario@gmail.com'),
       (2, 'luigi@gmail.com'),
       (3, 'toad@gmail.com'),
       (4, 'bowser@gmail.com'),
       (5, 'turtle@gmail.com'),
       (6, 'yoshi@gmail.com'),
       (7, 'wario@gmail.com'),
       (8, 'waluigi@gmail.com'),
       (9, 'rosalina@gmail.com'),
       (10, 'peach@gmail.com'),
       (11, 'daisy@gmail.com'),
       (12, 'kamek@gmail.com'),
       (13, 'birdo@gmail.com'),
       (14, 'chainchomp@gmail.com'),
       (15, 'admin@gmail.com');

INSERT INTO PERSON (id, first_name, last_name, email_id)
VALUES (1, 'Maro', 'Mario', 1),
       (2, 'Luigi', 'Mario', 2),
       (3, 'Toad', 'Mushroom', 3),
       (4, 'Bowser', 'Turtle', 4),
       (5, 'Turtle', 'Turtle', 5),
       (6, 'Yoshi', 'Dinosaur', 6),
       (7, 'Wario', 'Wario', 7),
       (8, 'Waluigi', 'Wario', 8),
       (9, 'Rosalina', 'Person', 9),
       (10, 'Peach', 'Princess', 10),
       (11, 'Daisy', 'Princess', 11),
       (12, 'Kamek', 'Witch', 12),
       (13, 'Birdo', 'Dinosaur', 13),
       (14, 'Chain Chomp', 'Creature', 14);

INSERT INTO PROFESSOR (id, age, phone_number)
VALUES (1, 22, '5105555555'),
       (2, 23, '5105551234'),
       (3, 24, '5105554321');

INSERT INTO COURSE (id, name)
VALUES (1, 'Linear Algebra'),
       (2, 'Machine Learning'),
       (3, 'Computer Vision');

INSERT INTO COURSE_PROFESSOR (course_id, professor_id)
VALUES (1, 1),
       (2, 1),
       (3, 3);

INSERT INTO STUDENT (id, grade_level)
VALUES (4, 12),
       (5, 12),
       (6, 12),
       (7, 12),
       (8, 12),
       (9, 12),
       (10, 12),
       (11, 12),
       (12, 12),
       (13, 12),
       (14, 12);


INSERT INTO COURSE_STUDENT (course_id, student_id)
VALUES (1, 4),
       (1, 6),
       (1, 9),
       (1, 10),
       (1, 13),
       (2, 4),
       (2, 5),
       (2, 7),
       (2, 8),
       (2, 12),
       (3, 5),
       (3, 6),
       (3, 8),
       (3, 9),
       (3, 13);

INSERT INTO ASSIGNMENT (id, name, possible_score, course_id)
VALUES (1, 'Kernel Filter', 10, 3),
       (4, 'Pyramid Image Processing', 10, 3),
       (2, 'Properties of a Linear Function', 10, 1),
       (3, 'Dimensionality Reduction', 10, 2);

INSERT INTO GRADE (id, score, assignment_id, student_id)
VALUES (1, 8, 1, 5),
       (2, 4, 1, 6),
       (3, 7, 1, 8),
       (4, 8, 1, 9),
       (5, 10, 1, 13);

INSERT INTO USERS (username, password, enabled)
VALUES ('admin_user', '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1),
       ('professor_user', '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1),
       ('student_user', '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1),
       ('turtle@gmail.com', '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1);

INSERT INTO AUTHORITIES (username, authority)
VALUES ('admin_user', 'ROLE_ADMIN'),
       ('professor_user', 'ROLE_PROFESSOR'),
       ('student_user', 'ROLE_STUDENT'),
       ('turtle@gmail.com', 'ROLE_STUDENT');

INSERT INTO ACL_CLASS (id, class)
VALUES (1, 'com.mortegagarcia.gradebook.model.Assignment'),
       (2, 'com.mortegagarcia.gradebook.model.Course'),
       (3, 'com.mortegagarcia.gradebook.model.Email'),
       (4, 'com.mortegagarcia.gradebook.model.Grade'),
       (5, 'com.mortegagarcia.gradebook.model.Person'),
       (6, 'com.mortegagarcia.gradebook.model.Professor'),
       (7, 'com.mortegagarcia.gradebook.model.Student');

INSERT INTO ACL_SID (id, principal, sid)
VALUES (1, 0, 'ROLE_ADMIN'),
       (2, 0, 'ROLE_PROFESSOR'),
       (3, 0, 'ROLE_STUDENT'),
--     This is an example of how to import a user
       (4, 1, 'mario@gmail.com'),
       (5, 1, 'luigi@gmail.com'),
       (6, 1, 'toad@gmail.com'),
       (7, 1, 'bowser@gmail.com'),
       (8, 1, 'turtle@gmail.com'),
       (9, 1, 'yoshi@gmail.com'),
       (10, 1, 'wario@gmail.com'),
       (11, 1, 'waluigi@gmail.com'),
       (12, 1, 'rosalina@gmail.com'),
       (13, 1, 'peach@gmail.com'),
       (14, 1, 'daisy@gmail.com'),
       (15, 1, 'kamek@gmail.com'),
       (16, 1, 'birdo@gmail.com'),
       (17, 1, 'chainchomp@gmail.com'),
       (18, 1, 'admin@gmail.com');

INSERT INTO ACL_OBJECT_IDENTITY (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
VALUES (1, 1, 1, NULL, 6, 1),
       (2, 1, 2, NULL, 4, 1),
       (3, 1, 3, NULL, 4, 1),
       (4, 1, 4, NULL, 6, 1),
       (5, 2, 1, NULL, 4, 1),
       (6, 2, 2, NULL, 4, 1),
       (7, 2, 3, NULL, 6, 1),
       (8, 3, 1, NULL, 4, 1),
       (9, 3, 2, NULL, 5, 1),
       (10, 3, 3, NULL, 6, 1),
       (11, 3, 4, NULL, 7, 1),
       (12, 3, 5, NULL, 8, 1),
       (13, 3, 6, NULL, 9, 1),
       (14, 3, 7, NULL, 10, 1),
       (15, 3, 8, NULL, 11, 1),
       (16, 3, 9, NULL, 12, 1),
       (17, 3, 10, NULL, 13, 1),
       (18, 3, 11, NULL, 14, 1),
       (19, 3, 12, NULL, 15, 1),
       (20, 3, 13, NULL, 16, 1),
       (21, 3, 14, NULL, 17, 1),
       (22, 3, 15, NULL, 18, 1),
       (23, 4, 1, NULL, 6, 1),
       (24, 4, 2, NULL, 6, 1),
       (25, 4, 3, NULL, 6, 1),
       (26, 4, 4, NULL, 6, 1),
       (27, 4, 5, NULL, 6, 1);

--        (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheritting),

INSERT INTO ACL_ENTRY (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
VALUES (1, 1, 1, 8, 1, 1, 1, 1),
       (2, 4, 2, 8, 1, 1, 1, 1);

--     (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, auditf_failure),

