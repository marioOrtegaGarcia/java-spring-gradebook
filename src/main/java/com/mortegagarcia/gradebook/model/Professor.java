package com.mortegagarcia.gradebook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "courses", "age", "phoneNumber" })
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int age;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToMany(mappedBy = "id", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    private List<Course> courses;

}
