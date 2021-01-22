package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository repo;

    @Autowired
    private AssignmentConverter conv;

    public List<AssignmentDTO> findAll() {
        List<Assignment> assignmentEntities = repo.findAll();
        return conv.entityToDTO(assignmentEntities);
    }

    public AssignmentDTO getOne(int id) {
        Assignment assignmentEntity = repo.getOne(id);
        return conv.entityToDTO(assignmentEntity);
    }

    public AssignmentDTO save(AssignmentDTO assignmentDTO) {
        Assignment assignmentEntity = repo.findById(assignmentDTO.getId()).orElse(null);
        if (assignmentEntity == null) assignmentEntity = conv.dtoToEntity(assignmentDTO);
        assignmentEntity = repo.save(assignmentEntity);
        return conv.entityToDTO(assignmentEntity);
    }

    public AssignmentDTO findById(Integer id) {
        Assignment assignmentEntity = repo.findById(id).orElse(null);
        return conv.entityToDTO(assignmentEntity);
    }

    public List<AssignmentDTO> findAssignmentByCourseID(Integer id) {
        List<Assignment> assignmentEntities = repo.findAssignmentByCourseID(id);
        return conv.entityToDTO(assignmentEntities);
    }

    public void delete(AssignmentDTO assignmentDTO) {
        Assignment assignment = repo.findById(assignmentDTO.getId()).orElse(null);
        if (assignment == null) return;
        repo.delete(assignment);
    }

    public AssignmentDTO update(AssignmentDTO assignmentDTO) {
        Assignment assignmentEntity = repo.findById(assignmentDTO.getId()).orElse(null);
        if (assignmentEntity == null) return null;
        assignmentEntity.setName(assignmentDTO.getName());
        assignmentEntity.setPossibleScore(assignmentDTO.getPossibleScore());
        assignmentEntity = repo.save(assignmentEntity);
        return conv.entityToDTO(assignmentEntity);
    }
}
