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

    public StudentDTO(String firstName, String lastName, int gradeLevel, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeLevel = gradeLevel;
        this.email = email;
    }
}
