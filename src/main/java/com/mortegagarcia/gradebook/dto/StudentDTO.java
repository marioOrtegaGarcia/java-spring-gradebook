package com.mortegagarcia.gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int id;

    private String firstName;

    private String lastName;

    private int gradeLevel;

    private String email;
}
