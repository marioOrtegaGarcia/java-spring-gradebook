CREATE DATABASE IF NOT EXISTS `school_gradebook`;
USE `school_gradebook`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `age` int(11) NOT NULL,
    `email` varchar(45) DEFAULT NULL,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `phone_number` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `professor_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `NAME_UNIQUE` (`name`),
  
  KEY `FK_PROFESSOR_idx` (`professor_id`),
  
  CONSTRAINT `FK_PROFESSOR` 
  FOREIGN KEY (`professor_id`) 
  REFERENCES `professor` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `grade_level` int(11) NOT NULL,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student` (
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  
  PRIMARY KEY (`course_id`,`student_id`),
  
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) 
  REFERENCES `student` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
    `id` INT(11) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `possible_score` INT(11) NOT NULL,
    `course_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `NAME_UNIQUE` (`name`),
    KEY `FK_COURSE_idx` (`course_id`),
    CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
    `score` INT(11) NOT NULL,
    `assignment_id` INT(11) NOT NULL,
    `student_id` INT(11) NOT NULL,
    PRIMARY KEY (`assignment_id` , `student_id`),
    KEY `FK_STUDENT_idx` (`student_id`),
    CONSTRAINT `FK_ASSIGNMENT_05` FOREIGN KEY (`assignment_id`)
        REFERENCES `assignment` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `FK_STUDENT_05` FOREIGN KEY (`student_id`)
        REFERENCES `student` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

SET FOREIGN_KEY_CHECKS = 1;
