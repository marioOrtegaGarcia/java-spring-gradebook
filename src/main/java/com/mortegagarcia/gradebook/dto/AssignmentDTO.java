package com.mortegagarcia.gradebook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignmentDTO {

    private int id;
    private String name;
    private int possibleScore;
    private int courseID;

    public AssignmentDTO(String name, int possibleScore, int courseID) {
        this.name = name;
        this.possibleScore = possibleScore;
        this.courseID = courseID;
    }
}
