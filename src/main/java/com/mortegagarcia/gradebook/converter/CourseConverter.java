package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {

    @Autowired
    AssignmentConverter conv;

    public CourseDTO entityToDTO(Course entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAssignments(conv.entityToDTO(entity.getAssignments()));
        return dto;
    }

    public List<CourseDTO> entityToDTO(List<Course> entity) {
        return entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Course dtoToEntity(CourseDTO dto) {
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAssignments(conv.dtoToEntity(dto.getAssignments()));
        return entity;
    }

    public List<Course> dtoToEntity(List<CourseDTO> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
