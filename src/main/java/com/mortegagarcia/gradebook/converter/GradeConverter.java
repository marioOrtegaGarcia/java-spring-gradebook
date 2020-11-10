package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Grade;

import org.springframework.stereotype.Component;

@Component
public class GradeConverter {

    public GradeDTO entityToDTO(Grade entity) {
        GradeDTO dto = new GradeDTO();
        dto.setId(entity.getId());
        dto.setScore(entity.getScore());
        // dto.setStudent(entity.getStudent());
        // dto.setAssignment(entity.getAssignment());
        return dto;
    }

    public List<GradeDTO> entityToDTO(List<Grade> entity) {
        return entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Grade dtoToEntity(GradeDTO dto) {
        Grade entity = new Grade();
        entity.setId(dto.getId());
        entity.setScore(dto.getScore());
        // entity.setStudent(dto.getStudent());
        // entity.setAssignment(dto.getAssignment());
        return entity;
    }

    public List<Grade> dtoToEntity(List<GradeDTO> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
