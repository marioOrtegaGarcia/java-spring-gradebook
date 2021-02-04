package com.mortegagarcia.gradebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "email")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;
}
