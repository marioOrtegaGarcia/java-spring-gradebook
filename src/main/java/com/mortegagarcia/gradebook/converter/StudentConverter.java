package com.mortegagarcia.gradebook.converter;

import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {

    public StudentDTO entityToDTO(Student entity) {
        if (entity == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, StudentDTO.class);
    }

    public List<StudentDTO> entityToDTO(List<Student> entity) {
        return (entity == null) ? null : entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Student dtoToEntity(StudentDTO dto) {
        if (dto == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Student.class);
    }

    public List<Student> dtoToEntity(List<StudentDTO> dto) {
        return (dto == null) ? null : dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
