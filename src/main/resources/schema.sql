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
--

CREATE
DATABASE IF NOT EXISTS school_gradebook;

USE
school_gradebook;

SET
FOREIGN_KEY_CHECKS = 0;

-- Schema
DROP TABLE if EXISTS `admin`;
DROP TABLE if EXISTS `assignment`;
DROP TABLE if EXISTS `course`;
DROP TABLE if EXISTS `course_professor`;
DROP TABLE if EXISTS `course_student`;
DROP TABLE if EXISTS `email`;
DROP TABLE if EXISTS `grade`;
DROP TABLE if EXISTS `person`;
DROP TABLE if EXISTS `professor`;
DROP TABLE if EXISTS `student`;

-- Security
DROP TABLE if EXISTS `users`;
DROP TABLE if EXISTS `authorities`;
DROP TABLE if EXISTS `acl_sid`;
DROP TABLE if EXISTS `acl_class`;
DROP TABLE if EXISTS `acl_object_identity`;
DROP TABLE if EXISTS `acl_entry`;

CREATE TABLE `admin`
(
    age          INT NOT NULL,
    phone_number VARCHAR(255) NULL,
    id           INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `assignment`
(
    id             INT AUTO_INCREMENT,
    name           VARCHAR(255) NULL,
    possible_score INT NOT NULL,
    course_id      INT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `course`
(
    id   INT AUTO_INCREMENT,
    name VARCHAR(255) NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `course_professor`
(
    professor_id INT NULL,
    course_id    INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `course_student`
(
    student_id INT NULL,
    course_id  INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `email`
(
    id    INT AUTO_INCREMENT,
    email VARCHAR(255) NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `grade`
(
    id            INT AUTO_INCREMENT,
    score         INT NOT NULL,
    assignment_id INT NULL,
    student_id    INT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `person`
(
    id         INT AUTO_INCREMENT,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    email_id   INT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `professor`
(
    age          INT NOT NULL,
    phone_number VARCHAR(255) NULL,
    id           INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `student`
(
    grade_level INT NOT NULL,
    id          INT NOT NULL
) ENGINE=InnoDB;

-- Security
CREATE TABLE `users`
(
    username VARCHAR(50) NOT NULL,
    password CHAR(68)    NOT NULL,
    enabled  tinyint(1) NOT NULL,
    PRIMARY KEY (username)
) ENGINE=InnoDB;

CREATE TABLE `authorities`
(
    username  varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    UNIQUE KEY unique_authorities_username_authority (username, authority),
    CONSTRAINT fk_authorities_username FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB;

CREATE TABLE `acl_sid`
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN      NOT NULL,
    sid       VARCHAR(100) NOT NULL,
    UNIQUE KEY unique_acl_sid (sid, principal)
) ENGINE=InnoDB;

CREATE TABLE `acl_class`
(
    id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100) NOT NULL,
    UNIQUE KEY uk_acl_class (class)
) ENGINE=InnoDB;

CREATE TABLE `acl_object_identity`
(
    id                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_id_class    BIGINT UNSIGNED NOT NULL,
    object_id_identity BIGINT  NOT NULL,
    parent_object      BIGINT UNSIGNED,
    owner_sid          BIGINT UNSIGNED,
    entries_inheriting BOOLEAN NOT NULL,
    UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
    CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

CREATE TABLE `acl_entry`
(
    id                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT UNSIGNED NOT NULL,
    ace_order           INTEGER NOT NULL,
    sid                 BIGINT UNSIGNED NOT NULL,
    mask                INTEGER UNSIGNED NOT NULL,
    granting            BOOLEAN NOT NULL,
    audit_success       BOOLEAN NOT NULL,
    audit_failure       BOOLEAN NOT NULL,
    UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
    CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

SET
FOREIGN_KEY_CHECKS = 1;
