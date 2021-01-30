package com.mortegagarcia.gradebook.converter;

import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Grade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradeConverter {

	public GradeDTO entityToDTO(Grade entity) {
		if (entity == null) return null;
		ModelMapper mapper = new ModelMapper();
		return mapper.map(entity, GradeDTO.class);
	}

	public List<GradeDTO> entityToDTO(List<Grade> entity) {
		return (entity == null) ? null : entity.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public Grade dtoToEntity(GradeDTO dto) {
		if (dto == null) return null;
		ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, Grade.class);
	}

	public List<Grade> dtoToEntity(List<GradeDTO> dto) {
		return (dto == null) ? null : dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}
}
