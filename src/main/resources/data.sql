-- CREATE DATABASE
-- IF NOT EXISTS `school_gradebook`;
-- USE `school_gradebook`;

-- SET FOREIGN_KEY_CHECKS = 0;

-- DROP TABLE IF EXISTS `professor`;
-- CREATE TABLE `professor` (
--     `id` INT(11) NOT NULL AUTO_INCREMENT,
--     `age` INT(11) NOT NULL,
--     `email` VARCHAR(45) DEFAULT NULL,
--     `first_name` VARCHAR(45) DEFAULT NULL,
--     `last_name` VARCHAR(45) DEFAULT NULL,
--     `phone_number` VARCHAR(45) DEFAULT NULL,
--     PRIMARY KEY (`id`)
-- )  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

-- DROP TABLE IF EXISTS `course`;
-- CREATE TABLE `course` (
--     `id` INT(11) NOT NULL AUTO_INCREMENT,
--     `name` VARCHAR(128) DEFAULT NULL,
-- --    `professor_id` INT(11) DEFAULT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `NAME_UNIQUE` (`name`)
-- --    KEY `FK_PROFESSOR` (`professor_id`)
-- )  ENGINE=INNODB AUTO_INCREMENT=100 DEFAULT CHARSET=LATIN1;

-- DROP TABLE IF EXISTS `student`;
-- CREATE TABLE `student` (
--     `id` INT(11) NOT NULL AUTO_INCREMENT,
--     `grade_level` INT(11) NOT NULL,
--     `first_name` VARCHAR(45) DEFAULT NULL,
--     `last_name` VARCHAR(45) DEFAULT NULL,
--     `email` VARCHAR(45) DEFAULT NULL,
--     PRIMARY KEY (`id`)
-- )  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

-- DROP TABLE IF EXISTS `course_student`;
-- CREATE TABLE `course_student` (
--     `course_id` INT(11) NOT NULL,
--     `student_id` INT(11) NOT NULL,
--     PRIMARY KEY (`course_id` , `student_id`),
--     KEY `FK_STUDENT` (`student_id`),
--     CONSTRAINT `FK_COURSE_STUDENT` FOREIGN KEY (`course_id`)
--         REFERENCES `course` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
--         REFERENCES `student` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;

-- DROP TABLE IF EXISTS `course_professor`;
-- CREATE TABLE `course_professor` (
--     `course_id` INT(11) NOT NULL,
--     `professor_id` INT(11) NOT NULL,
--     PRIMARY KEY (`course_id` , `professor_id`),
--     KEY `FK_PROFESSOR` (`professor_id`),
--     CONSTRAINT `FK_COURSE_IDX` FOREIGN KEY (`course_id`)
--         REFERENCES `course` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_PROFESSOR` FOREIGN KEY (`professor_id`)
--         REFERENCES `professor` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;

-- DROP TABLE IF EXISTS `assignment`;
-- CREATE TABLE `assignment` (
--     `id` INT(11) NOT NULL,
--     `name` VARCHAR(45) NOT NULL,
--     `possible_score` INT(11) NOT NULL,
--     `course_id` INT(11) NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `NAME_UNIQUE` (`name`),
--     KEY `FK_COURSE` (`course_id`),
--     CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`)
--         REFERENCES `course` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;

-- DROP TABLE IF EXISTS `grade`;
-- CREATE TABLE `grade` (
-- 	`id` INT(11) NOT NULL auto_increment,
--     `score` INT(11) NOT NULL,
--     `assignment_id` INT(11) NOT NULL,
--     `student_id` INT(11) NOT NULL,
--     PRIMARY KEY (`id`),
--     KEY `FK_STUDENT_GRADE` (`student_id`),
--     CONSTRAINT `FK_ASSIGNMENT` FOREIGN KEY (`assignment_id`)
--         REFERENCES `assignment` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION,
--     CONSTRAINT `FK_STUDENT_GRADE` FOREIGN KEY (`student_id`)
--         REFERENCES `student` (`id`)
--         ON DELETE NO ACTION ON UPDATE NO ACTION
-- )  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

-- SET FOREIGN_KEY_CHECKS = 1;


-- INSERT INTO PROFESSOR
-- VALUES
--   (1, 22, 'mario@gmail.com', 'Mario', 'Mario', '5105555555'),
--   (2, 23, 'luigi@gmail.com', 'Luigi', 'Mario', '5105551234'),
--   (3, 24, 'toad@gmail.com', 'Toad', 'Mushroom', '5105554321');

-- INSERT INTO COURSE
-- VALUES
--   (1, 'Linear Algebra'),
--   (2, 'Machine Learning'),
--   (3, 'Computer Vision');
  
-- INSERT INTO COURSE_PROFESSOR
-- VALUES
-- (1,1),
-- (2,1),
-- (3,3);

-- INSERT INTO STUDENT
-- VALUES
--   (1, 12, 'Bowser', 'Turtle', 'bowser@gmail.com'),
--   (2, 12, 'Turtle', 'Turtle', 'turtle@gmail.com'),
--   (3, 12, 'Yoshi', 'Dinosaur', 'yoshi@gmail.com'),
--   (4, 12, 'Wario', 'Wario', 'wario@gmail.com'),
--   (5, 12, 'Waluigi', 'Wario', 'waluigi@gmail.com'),
--   (6, 12, 'Rosalina', 'Person', 'rosalia@gmail.com'),
--   (7, 12, 'Peach', 'Princess', 'peach@gmail.com'),
--   (8, 12, 'Daisy', 'Princess', 'daidy@gmail.com'),
--   (9, 12, 'Kamek', 'Witch', 'kamek@gmail.com'),
--   (10, 12, 'Birdo', 'Dinosaur', 'birdo@gmail.com'),
--   (11, 12, 'Chain Chomp', 'Creature', 'chainchomp@gmail.com');

-- INSERT INTO COURSE_STUDENT
-- VALUES
--   (1, 1),
--   (1, 3),
--   (1, 6),
--   (1, 7),
--   (1, 10),
--   (2, 1),
--   (2, 2),
--   (2, 4),
--   (2, 5),
--   (2, 9),
--   (3, 2),
--   (3, 3),
--   (3, 5),
--   (3, 6),
--   (3, 10);

-- INSERT INTO ASSIGNMENT
-- VALUES
--   (1, 'Kernel Filter', 10, 3),
--   (4, 'Pyramid Image Processing', 10, 3),
--   (2, 'Properties of a Linear Function', 10, 1),
--   (3, 'Dimensionality Reduction', 10, 2);

-- INSERT INTO GRADE
-- VALUES
--   (1, 8, 1, 2),
--   (2, 4, 1, 3),
--   (3, 7, 1, 5),
--   (4, 8, 1, 6),
--   (5, 10, 1, 10);
