package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.model.Role;
import com.mortegagarcia.gradebook.model.User;
import com.mortegagarcia.gradebook.repository.RoleRepository;
import com.mortegagarcia.gradebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

import static java.util.Arrays.asList;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

//	public User findUserByEmail(String email) {
//		return userRepository.findByEmail(email);
//	}

	public User findUserByUserName(String userName) throws UsernameNotFoundException {
		return userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User with username " + userName + " not found."));
	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
		return userRepository.save(user);
	}
}
