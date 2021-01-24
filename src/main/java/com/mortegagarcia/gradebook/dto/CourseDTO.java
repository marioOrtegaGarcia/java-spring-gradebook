package com.mortegagarcia.gradebook.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private int id;

    private String name;

    private List<AssignmentDTO> assignments;
}
