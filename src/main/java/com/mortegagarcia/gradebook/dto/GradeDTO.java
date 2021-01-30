package com.mortegagarcia.gradebook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class GradeDTO {

	private int id;
	private int score;
	private int studentID;
	private int assignmentID;

	public GradeDTO(int score, int studentID, int assignmentID) {
		this.score = score;
		this.studentID = studentID;
		this.assignmentID = assignmentID;
	}
}
