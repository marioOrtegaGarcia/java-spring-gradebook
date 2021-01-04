package com.mortegagarcia.gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
