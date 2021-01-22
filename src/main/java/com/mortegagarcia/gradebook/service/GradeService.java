package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository repo;

    @Autowired
    private GradeConverter gConv;

    @Autowired
    private StudentConverter sConv;

    @Autowired
    private AssignmentConverter aConv;

    public List<GradeDTO> findAll() {
        List<Grade> gradeEntities = repo.findAll();
        return gConv.entityToDTO(gradeEntities);
    }

    public GradeDTO save(GradeDTO gradeDTO) {
        Grade gradeEntity = repo.findById(gradeDTO.getId()).orElse(null);
        if(gradeEntity == null) gradeEntity = gConv.dtoToEntity(gradeDTO);
        gradeEntity = repo.save(gradeEntity);
        return gConv.entityToDTO(gradeEntity);
    }

    public GradeDTO getOne(int id) {
        Grade gradeEntity = repo.getOne(id);
        return gConv.entityToDTO(gradeEntity);
    }

    public GradeDTO findById(Integer id) {
        Grade gradeEntity = repo.findById(id).orElse(null);
        return gConv.entityToDTO(gradeEntity);
    }

    public List<GradeDTO> findGradeByAssignmentID(Integer id) {
        List<Grade> gradeEntities = repo.findGradeByAssignmentID(id);
        return gConv.entityToDTO(gradeEntities);
    }

    public Double findAssignmentAverageScore(Integer id) {
        return repo.findAssignmentAverageScore(id);
    }

    public Double findAssignmentMaximumScore(Integer id) {
        return repo.findAssignmentMaximumScore(id);
    }

    public Double findAssignmentMinimumScore(Integer id) {
        return repo.findAssignmentMinimumScore(id);
    }

    public void delete(GradeDTO gradeDTO) {
        Grade gradeEntity = repo.findById(gradeDTO.getId()).orElse(null);
        if (gradeEntity == null) return;
        repo.delete(gradeEntity);
    }

    public GradeDTO update(GradeDTO dto) {
        Grade gradeEntity = repo.findById(dto.getId()).orElse(null);
        if (gradeEntity == null) return null;
        gradeEntity.setScore(dto.getScore());
        gradeEntity.setStudent(sConv.dtoToEntity(dto.getStudent()));
        gradeEntity.setAssignment(aConv.dtoToEntity(dto.getAssignment()));
        gradeEntity = repo.save(gradeEntity);
        return gConv.entityToDTO(gradeEntity);
    }
}
