package com.mortegagarcia.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"professor", "students", "assignments"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinTable(name = "course_professor", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private Professor professor;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @ToString.Exclude
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

}
