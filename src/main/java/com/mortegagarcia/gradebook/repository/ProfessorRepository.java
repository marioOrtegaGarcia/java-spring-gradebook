package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	@Query
	List<Professor> findProfessorByFirstName(String firstName);

}
