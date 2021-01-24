package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AssignmentConverter aConv;

    public List<AssignmentDTO> findAll() {
        List<Assignment> assignmentEntities = assignmentRepository.findAll();
        return aConv.entityToDTO(assignmentEntities);
    }

    public AssignmentDTO getOne(int id) {
        Assignment assignmentEntity = assignmentRepository.getOne(id);
        return aConv.entityToDTO(assignmentEntity);
    }

    public AssignmentDTO save(AssignmentDTO assignmentDTO) {
        Assignment assignmentEntity = assignmentRepository.findById(assignmentDTO.getId()).orElse(null);
        if (assignmentEntity == null) assignmentEntity = aConv.dtoToEntity(assignmentDTO);
        Course course = courseRepository.findById(assignmentDTO.getCourseID()).orElse(null);
        if(course == null) return null;
        assignmentEntity.setCourse(course);
        try {
            assignmentEntity = assignmentRepository.save(assignmentEntity);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return aConv.entityToDTO(assignmentEntity);
    }

    public AssignmentDTO findById(Integer id) {
        Assignment assignmentEntity = assignmentRepository.findById(id).orElse(null);
        return aConv.entityToDTO(assignmentEntity);
    }

    public List<AssignmentDTO> findAssignmentByCourseID(Integer id) {
        List<Assignment> assignmentEntities = assignmentRepository.findAssignmentByCourseID(id);
        return aConv.entityToDTO(assignmentEntities);
    }

    public void delete(AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentRepository.findById(assignmentDTO.getId()).orElse(null);
        if (assignment == null) return;
        assignmentRepository.delete(assignment);
    }

    public AssignmentDTO update(AssignmentDTO assignmentDTO) {
        Assignment assignmentEntity = assignmentRepository.findById(assignmentDTO.getId()).orElse(null);
        if (assignmentEntity == null) {
            assignmentEntity = aConv.dtoToEntity(assignmentDTO);
        } else {
            assignmentEntity.setName(assignmentDTO.getName());
            assignmentEntity.setPossibleScore(assignmentDTO.getPossibleScore());
        }
        Course course = courseRepository.findById(assignmentDTO.getCourseID()).orElse(null);
        if(course == null) return null;
        assignmentEntity.setCourse(course);
        assignmentEntity = assignmentRepository.save(assignmentEntity);
        return aConv.entityToDTO(assignmentEntity);
    }

    public Double getAssignmentMinimumGrade(Integer assignmentID) {
        return assignmentRepository.getAssignmentMinimumGrade(assignmentID);
    }

    public Double getAssignmentAverageGrade(Integer assignmentID) {
        return assignmentRepository.getAssignmentAverageGrade(assignmentID);
    }

    public Double getAssignmentMaximumGrade(Integer assignmentID) {
        return assignmentRepository.getAssignmentMaximumGrade(assignmentID);
    }

}
