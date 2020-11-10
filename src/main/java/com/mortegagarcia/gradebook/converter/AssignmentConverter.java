package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;

import org.springframework.stereotype.Component;

@Component
public class AssignmentConverter {

    public AssignmentDTO entityToDTO(Assignment entity) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPossibleScore(entity.getPossibleScore());
        return dto;
    }

    public List<AssignmentDTO> entityToDTO(List<Assignment> entity) {
        return entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Assignment dtoToEntity(AssignmentDTO dto) {
        Assignment entity = new Assignment();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPossibleScore(dto.getPossibleScore());
        return entity;
    }

    public List<Assignment> dtoToEntity(List<AssignmentDTO> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
