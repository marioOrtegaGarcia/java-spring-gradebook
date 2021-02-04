-- CREATE
-- DATABASE
-- 	IF NOT EXISTS `school_gradebook`;
-- USE
-- `school_gradebook`;
--
-- SET
-- FOREIGN_KEY_CHECKS = 0;
--
-- DROP TABLE IF EXISTS `professor`;
-- CREATE TABLE `professor`
-- (
--     `id`           INT(11) NOT NULL AUTO_INCREMENT,
--     `age`          INT(11) NOT NULL,
--     `email`        VARCHAR(45) DEFAULT NULL,
--     `first_name`   VARCHAR(45) DEFAULT NULL,
--     `last_name`    VARCHAR(45) DEFAULT NULL,
--     `phone_number` VARCHAR(45) DEFAULT NULL,
--     PRIMARY KEY (`id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `course`;
-- CREATE TABLE `course`
-- (
--     `id`   INT(11) NOT NULL AUTO_INCREMENT,
--     `name` VARCHAR(128) DEFAULT NULL,
-- --    `professor_id` INT(11) DEFAULT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `NAME_UNIQUE` (`name`)
-- --    KEY `FK_PROFESSOR` (`professor_id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 100
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `student`;
-- CREATE TABLE `student`
-- (
--     `id`          INT(11) NOT NULL AUTO_INCREMENT,
--     `grade_level` INT(11) NOT NULL,
--     `first_name`  VARCHAR(45) DEFAULT NULL,
--     `last_name`   VARCHAR(45) DEFAULT NULL,
--     `email`       VARCHAR(45) DEFAULT NULL,
--     PRIMARY KEY (`id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `course_student`;
-- CREATE TABLE `course_student`
-- (
--     `course_id`  INT(11) NOT NULL,
--     `student_id` INT(11) NOT NULL,
--     PRIMARY KEY (`course_id`, `student_id`),
--     KEY `FK_STUDENT` (`student_id`),
--     CONSTRAINT `FK_COURSE_STUDENT` FOREIGN KEY (`course_id`)
--         REFERENCES `course` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
--         REFERENCES `student` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE = INNODB
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `course_professor`;
-- CREATE TABLE `course_professor`
-- (
--     `course_id`    INT(11) NOT NULL,
--     `professor_id` INT(11) NOT NULL,
--     PRIMARY KEY (`course_id`, `professor_id`),
--     KEY `FK_PROFESSOR` (`professor_id`),
--     CONSTRAINT `FK_COURSE_IDX` FOREIGN KEY (`course_id`)
--         REFERENCES `course` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_PROFESSOR` FOREIGN KEY (`professor_id`)
--         REFERENCES `professor` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE = INNODB
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `assignment`;
-- CREATE TABLE `assignment`
-- (
--     `id`             INT(11)     NOT NULL AUTO_INCREMENT,
--     `name`           VARCHAR(45) NOT NULL,
--     `possible_score` INT(11)     NOT NULL,
--     `course_id`      INT(11)     NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `NAME_UNIQUE` (`name`),
--     KEY `FK_COURSE` (`course_id`),
--     CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`)
--         REFERENCES `course` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE = INNODB
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `grade`;
-- CREATE TABLE `grade`
-- (
--     `id`            INT(11) NOT NULL auto_increment,
--     `score`         INT(11) NOT NULL,
--     `assignment_id` INT(11) NOT NULL,
--     `student_id`    INT(11) NOT NULL,
--     PRIMARY KEY (`id`),
--     KEY `FK_STUDENT_GRADE` (`student_id`),
--     CONSTRAINT `FK_ASSIGNMENT` FOREIGN KEY (`assignment_id`)
--         REFERENCES `assignment` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_STUDENT_GRADE` FOREIGN KEY (`student_id`)
--         REFERENCES `student` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = LATIN1;
--
-- -- SPRING SECURITY TABLES
--
-- DROP TABLE IF EXISTS `user`;
-- CREATE TABLE `user`
-- (
--     `id`       INT(11)     NOT NULL auto_increment,
--     `username` varchar(50) NOT NULL UNIQUE,
--     # 	`email`    varchar(50) NOT NULL UNIQUE,
--     `password` varchar(68) NOT NULL,
--     `enabled`  tinyint(1)  NOT NULL,
--     PRIMARY KEY (`id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `role`;
-- CREATE TABLE `role`
-- (
--     `id`   INT(11)     NOT NULL auto_increment,
--     `role` varchar(50) NOT NULL UNIQUE,
--     PRIMARY KEY (`id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = LATIN1;
--
-- DROP TABLE IF EXISTS `user_role`;
-- CREATE TABLE `user_role`
-- (
--     `user_id` INT(11) NOT NULL,
--     `role_id` INT(11) NOT NULL,
--     PRIMARY KEY (`user_id`, `role_id`),
--     KEY `FK_USER` (`user_id`),
--     CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`)
--         REFERENCES `user` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
--         REFERENCES `role` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE = INNODB
--   DEFAULT CHARSET = LATIN1;
--
-- SET
-- FOREIGN_KEY_CHECKS = 1;
--

INSERT INTO PROFESSOR (id, age, email_id, first_name, last_name, phone_number)
VALUES (1, 22, 1, 'Mario', 'Mario', '5105555555'),
       (2, 23, 2, 'Luigi', 'Mario', '5105551234'),
       (3, 24, 3, 'Toad', 'Mushroom', '5105554321');

INSERT INTO COURSE (id, name)
VALUES (1, 'Linear Algebra'),
       (2, 'Machine Learning'),
       (3, 'Computer Vision');

INSERT INTO COURSE_PROFESSOR (course_id, professor_id)
VALUES (1, 1),
       (2, 1),
       (3, 3);

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
       (14, 'chainchomp@gmail.com');



INSERT INTO STUDENT (id, grade_level, first_name, last_name, email_id)
VALUES (1, 12, 'Bowser', 'Turtle', 4),
       (2, 12, 'Turtle', 'Turtle', 5),
       (3, 12, 'Yoshi', 'Dinosaur', 6),
       (4, 12, 'Wario', 'Wario', 7),
       (5, 12, 'Waluigi', 'Wario', 8),
       (6, 12, 'Rosalina', 'Person', 9),
       (7, 12, 'Peach', 'Princess', 10),
       (8, 12, 'Daisy', 'Princess', 11),
       (9, 12, 'Kamek', 'Witch', 12),
       (10, 12, 'Birdo', 'Dinosaur', 13),
       (11, 12, 'Chain Chomp', 'Creature', 14);

INSERT INTO COURSE_STUDENT (course_id, student_id)
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

INSERT INTO ASSIGNMENT (id, name, possible_score, course_id)
VALUES (1, 'Kernel Filter', 10, 3),
       (4, 'Pyramid Image Processing', 10, 3),
       (2, 'Properties of a Linear Function', 10, 1),
       (3, 'Dimensionality Reduction', 10, 2);

INSERT INTO GRADE (id, score, assignment_id, student_id)
VALUES (1, 8, 1, 2),
       (2, 4, 1, 3),
       (3, 7, 1, 5),
       (4, 8, 1, 6),
       (5, 10, 1, 10);

INSERT INTO `user` (id, username, email_id, password, enabled)
VALUES (1, 'admin_user', null, '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1),
       (2, 'professor_user', 1, '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1),
       (3, 'student_user', 4, '{bcrypt}$2y$12$APuz15agP4fEVKCiE26i.OtyKytjUQxCYToUiWw5qw04MetcbvEPa', 1);

INSERT INTO `role` (id, role)
VALUES (1, 'ADMIN'),
       (2, 'PROFESSOR'),
       (3, 'STUDENT');

INSERT INTO `user_role` (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 3);
