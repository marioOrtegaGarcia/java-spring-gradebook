package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.ProfessorConverter;
import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.model.Professor;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import com.mortegagarcia.gradebook.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repo;

    @Autowired
    private CourseRepository cRepo;

    @Autowired
    private ProfessorConverter conv;

    public List<ProfessorDTO> findAll() {
        List<Professor> professorEntities = repo.findAll();
        return conv.entityToDTO(professorEntities);
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        Professor professorEntity = repo.findById(professorDTO.getId()).orElse(null);
        if (professorEntity == null) professorEntity = conv.dtoToEntity(professorDTO);
        professorEntity = repo.save(professorEntity);
        return conv.entityToDTO(professorEntity);
    }

    public ProfessorDTO findById(Integer id) {
        Professor professorEntity = repo.findById(id).orElse(null);
        return conv.entityToDTO(professorEntity);
    }

    public ProfessorDTO getOne(Integer id) {
        Professor professorEntity = repo.getOne(id);
        return conv.entityToDTO(professorEntity);
    }

    public void delete(ProfessorDTO professorDTO) {
        Professor professorEntity = repo.findById(professorDTO.getId()).orElse(null);
        if (professorEntity == null) return;
        repo.delete(professorEntity);
    }

    public void deleteCoursesByProfessor(ProfessorDTO professorDTO) {
        List<Course> courseEntities = cRepo.findCoursesByProfessorID(professorDTO.getId());
        for (Course courseEntity : courseEntities) {
            cRepo.delete(courseEntity);
        }
    }

    public ProfessorDTO update(ProfessorDTO professorDTO) {
        Professor professorEntity = repo.findById(professorDTO.getId()).orElse(null);
        if (professorEntity == null) return null;
        professorEntity.setFirstName(professorDTO.getFirstName());
        professorEntity.setLastName(professorDTO.getLastName());
        professorEntity.setEmail(professorDTO.getEmail());
        professorEntity.setPhoneNumber(professorDTO.getPhoneNumber());
        professorEntity = repo.save(professorEntity);
        return conv.entityToDTO(professorEntity);
    }
}