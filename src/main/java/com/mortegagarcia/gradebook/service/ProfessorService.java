package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.ProfessorConverter;
import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.model.Email;
import com.mortegagarcia.gradebook.model.Professor;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import com.mortegagarcia.gradebook.repository.EmailRepository;
import com.mortegagarcia.gradebook.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final CourseRepository courseRepository;
	private final EmailRepository emailRepository;
	private final ProfessorConverter conv;

	public List<ProfessorDTO> findAll() {
		List<Professor> professorEntities = professorRepository.findAll();
		return conv.entityToDTO(professorEntities);
	}

	public ProfessorDTO save(ProfessorDTO professorDTO) {
		Professor professorEntity = saveNewEntity(professorDTO);
		return conv.entityToDTO(professorEntity);
	}

	public ProfessorDTO findById(Integer id) {
		Professor professorEntity = professorRepository.findById(id)
				.orElseThrow(IllegalArgumentException::new);
		return conv.entityToDTO(professorEntity);
	}

	public ProfessorDTO getOne(Integer id) {
		Professor professorEntity = professorRepository.getOne(id);
		return conv.entityToDTO(professorEntity);
	}

	public void delete(ProfessorDTO professorDTO) {
		Professor professorEntity = professorRepository.findById(professorDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		professorRepository.delete(professorEntity);
	}

	public void deleteCoursesByProfessor(ProfessorDTO professorDTO) {
		courseRepository.deleteCourseByProfessorId(professorDTO.getId());
	}

	public ProfessorDTO update(Integer professorID, ProfessorDTO professorDTO) {
		Professor professorEntity = professorRepository.findById(professorID).orElse(null);
		if (professorEntity == null) professorEntity = saveNewEntity(professorDTO);
		else professorEntity = saveExistingEntity(professorEntity, professorDTO);
		return conv.entityToDTO(professorEntity);
	}

	private Professor saveExistingEntity(Professor professorEntity, ProfessorDTO professorDTO) {
		professorEntity.setFirstName(professorDTO.getFirstName());
		professorEntity.setLastName(professorDTO.getLastName());
		Email email = emailRepository.findEmailByEmail(professorDTO.getEmail())
				.orElse(new Email().setEmail(professorDTO.getEmail()));
		professorEntity.setEmail(email);
		professorEntity.setPhoneNumber(professorDTO.getPhoneNumber());
		professorEntity = professorRepository.save(professorEntity);
		return professorEntity;
	}

	private Professor saveNewEntity(ProfessorDTO professorDTO) {
		Professor professorEntity = conv.dtoToEntity(professorDTO);
		professorEntity = professorRepository.save(professorEntity);
		return professorEntity;
	}

}
