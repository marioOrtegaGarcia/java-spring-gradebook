package com.mortegagarcia.gradebook.dto;

import lombok.Data;

@Data
public class AssignmentDTO {
    private int id;

    private String name;

    private int possibleScore;
}
