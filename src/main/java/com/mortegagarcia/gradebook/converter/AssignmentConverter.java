package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AssignmentConverter {

    public AssignmentDTO entityToDTO(Assignment entity) {
        if (entity == null)
            return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, AssignmentDTO.class);
    }

    public List<AssignmentDTO> entityToDTO(List<Assignment> entity) {
        return (entity == null) ? null : entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Assignment dtoToEntity(AssignmentDTO dto) {
        if (dto == null)
            return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Assignment.class);
    }

    public List<Assignment> dtoToEntity(List<AssignmentDTO> dto) {
        return (dto == null) ? null : dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
