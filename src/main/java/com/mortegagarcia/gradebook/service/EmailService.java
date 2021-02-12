package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.model.Email;
import com.mortegagarcia.gradebook.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

	private final EmailRepository emailRepository;

	public Email findEmailByEmail(String email) {
		return emailRepository.findEmailByEmail(email).orElseThrow(Error::new);
	}
}
