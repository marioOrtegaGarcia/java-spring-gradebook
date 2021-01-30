package com.mortegagarcia.gradebook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class ProfessorDTO {

	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;

	public ProfessorDTO(String email, String firstName, String lastName, String phoneNumber) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
}
