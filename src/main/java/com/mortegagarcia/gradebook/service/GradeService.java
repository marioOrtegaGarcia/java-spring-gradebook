package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import com.mortegagarcia.gradebook.repository.GradeRepository;
import com.mortegagarcia.gradebook.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private GradeConverter gConv;

    public List<GradeDTO> findAll() {
        List<Grade> gradeEntities = gradeRepository.findAll();
        return gConv.entityToDTO(gradeEntities);
    }

    public GradeDTO save(GradeDTO gradeDTO) {
        Grade gradeEntity = gradeRepository.findById(gradeDTO.getId()).orElse(null);
        if (gradeEntity == null) gradeEntity = gConv.dtoToEntity(gradeDTO);

        Student studentEntity = studentRepository.findById(gradeDTO.getStudentID()).orElse(null);
        if (studentEntity == null) return null;
        Assignment assignmentEntity = assignmentRepository.findById(gradeDTO.getAssignmentID()).orElse(null);
        if (assignmentEntity == null) return null;

        gradeEntity.setStudent(studentEntity);
        gradeEntity.setAssignment(assignmentEntity);

        gradeEntity = gradeRepository.save(gradeEntity);
        return gConv.entityToDTO(gradeEntity);
    }

    public GradeDTO getOne(int id) {
        Grade gradeEntity = gradeRepository.getOne(id);
        return gConv.entityToDTO(gradeEntity);
    }

    public GradeDTO findById(Integer id) {
        Grade gradeEntity = gradeRepository.findById(id).orElse(null);
        return gConv.entityToDTO(gradeEntity);
    }

    public void delete(GradeDTO gradeDTO) {
        Grade gradeEntity = gradeRepository.findById(gradeDTO.getId()).orElse(null);
        if (gradeEntity == null) return;
        gradeRepository.delete(gradeEntity);
    }

    public GradeDTO update(GradeDTO dto) {
        Grade gradeEntity = gradeRepository.findById(dto.getId()).orElse(null);
        if (gradeEntity == null) gradeEntity = gConv.dtoToEntity(dto);

        Student studentEntity = studentRepository.findById(dto.getStudentID()).orElse(null);
        Assignment assignmentEntity = assignmentRepository.findById(dto.getAssignmentID()).orElse(null);

        if(studentEntity == null || assignmentEntity == null) return null;
        Course courseEntity = assignmentEntity.getCourse();
        if(!courseEntity.getAssignments().contains(assignmentEntity) || !courseEntity.getStudents().contains(studentEntity)) return null;

        gradeEntity.setScore(dto.getScore());
        gradeEntity.setStudent(studentEntity);
        gradeEntity.setAssignment(assignmentEntity);
        gradeEntity = gradeRepository.save(gradeEntity);
        return gConv.entityToDTO(gradeEntity);
    }
}
