package com.mortegagarcia.gradebook.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;

    private String firstName;

    private String lastName;

    private int gradeLevel;

    private String email;
}
