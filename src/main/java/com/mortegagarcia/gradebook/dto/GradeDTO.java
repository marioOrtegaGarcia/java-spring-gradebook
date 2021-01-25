package com.mortegagarcia.gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
