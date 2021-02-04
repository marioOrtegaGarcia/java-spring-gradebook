package com.mortegagarcia.gradebook.repository;

import com.mortegagarcia.gradebook.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Integer> {
	Optional<Email> findEmailByEmail(String email);
}
