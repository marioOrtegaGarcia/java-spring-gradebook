package com.mortegagarcia.gradebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.mortegagarcia.gradebook.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    @Query("SELECT p FROM Professor p WHERE p.firstName = ?1")
    List<Professor> findProfesssorByFirstName(String firstName);

}
