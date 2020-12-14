package com.mortegagarcia.gradebook.dto;

import lombok.Data;

@Data
public class GradeDTO {

    private int id;

    private int score;

    private StudentDTO student;

    private AssignmentDTO assignment;

}
