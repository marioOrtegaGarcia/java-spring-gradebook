package com.mortegagarcia.gradebook.dto;

import lombok.Data;

@Data
public class ProfessorDTO {
    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
