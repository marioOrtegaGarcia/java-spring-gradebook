package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
