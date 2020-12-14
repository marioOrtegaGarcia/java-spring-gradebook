package com.mortegagarcia.gradebook.dto;

import java.util.List;

import lombok.Data;

@Data
public class CourseDTO {

    private int id;

    private String name;

    private List<AssignmentDTO> assignments;
}
