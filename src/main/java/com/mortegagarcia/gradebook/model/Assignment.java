package com.mortegagarcia.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assignment")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"grades", "course"})
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int possibleScore;

    @ManyToOne
    private Course course;

    @ToString.Exclude
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<Grade> grades;

}
