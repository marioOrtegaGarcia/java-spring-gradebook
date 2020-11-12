package com.mortegagarcia.gradebook.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.model.Professor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfessorConverter {

    public ProfessorDTO entityToDTO(Professor entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, ProfessorDTO.class);
    }

    public List<ProfessorDTO> entityToDTO(List<Professor> entity) {
        return entity.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Professor dtoToEntity(ProfessorDTO dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Professor.class);
    }

    public List<Professor> dtoToEntity(List<ProfessorDTO> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}