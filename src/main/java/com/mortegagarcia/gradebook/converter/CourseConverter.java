package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Course;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {

    @Autowired
    AssignmentConverter conv;

    public CourseDTO entityToDTO(Course entity) {
        if (entity == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, CourseDTO.class);
    }

    public List<CourseDTO> entityToDTO(List<Course> entity) {
        return (entity == null) ? null : entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Course dtoToEntity(CourseDTO dto) {
        if (dto == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Course.class);
    }

    public List<Course> dtoToEntity(List<CourseDTO> dto) {
        return (dto == null) ? null : dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
