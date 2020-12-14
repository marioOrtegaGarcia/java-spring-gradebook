package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Student;

import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public StudentDTO entityToDTO(Student entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setGradeLevel(entity.getGradeLevel());
        return dto;
    }

    public List<StudentDTO> entityToDTO(List<Student> entity) {
        return entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Student dtoToEntity(StudentDTO dto) {
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGradeLevel(dto.getGradeLevel());
        return entity;
    }

    public List<Student> dtoToEntity(List<StudentDTO> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
