package com.mortegagarcia.gradebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "grade")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int score;

	@ManyToOne
	@NonNull
	private Student student;

	@ManyToOne
	@NonNull
	private Assignment assignment;

}
