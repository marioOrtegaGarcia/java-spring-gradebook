package com.mortegagarcia.gradebook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CourseDTO {

    private int id;
    private String name;
    private List<AssignmentDTO> assignments;

    public CourseDTO(String name, List<AssignmentDTO> assignments) {
        this.name = name;
        this.assignments = assignments;
    }
}
