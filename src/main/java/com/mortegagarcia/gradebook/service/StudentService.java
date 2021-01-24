package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;
import com.mortegagarcia.gradebook.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter sConv;

    @Autowired
    private GradeConverter gConv;

    public List<StudentDTO> findAll() {
        List<Student> studentEntities = studentRepository.findAll();
        return sConv.entityToDTO(studentEntities);
    }

    public StudentDTO save(StudentDTO studentDTO) {
        Student studentEntity = studentRepository.findById(studentDTO.getId()).orElse(null);
        if (studentEntity == null) studentEntity = sConv.dtoToEntity(studentDTO);
        studentEntity = studentRepository.save(studentEntity);
        return sConv.entityToDTO(studentEntity);
    }

    public StudentDTO getOne(int id) {
        Student studentEntity = studentRepository.getOne(id);
        return sConv.entityToDTO(studentEntity);
    }

    public StudentDTO findById(Integer id) {
        Student studentEntity = studentRepository.findById(id).orElse(null);
        return sConv.entityToDTO(studentEntity);
    }

    public void delete(StudentDTO studentDTO) {
        Student studentEntity = studentRepository.findById(studentDTO.getId()).orElse(null);
        if (studentEntity == null) return;
        studentRepository.delete(studentEntity);
    }

    public StudentDTO update(StudentDTO studentDTO) {
        Student studentEntity = studentRepository.findById(studentDTO.getId()).orElse(null);
        if (studentEntity == null) studentEntity = sConv.dtoToEntity(studentDTO);
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setGradeLevel(studentDTO.getGradeLevel());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity = studentRepository.save(studentEntity);
        return sConv.entityToDTO(studentEntity);
    }

    public GradeDTO getStudentAssignmentGrade(Integer studentID, Integer assignmentID) {
        Grade gradeDTO = studentRepository.getStudentAssignmentGrade(studentID, assignmentID);
        return gConv.entityToDTO(gradeDTO);
    }

    public Character getStudentAssignmentLetterGrade(Integer studentID, Integer assignmentID) {
        return studentRepository.getStudentAssignmentLetterGrade(studentID, assignmentID);
    }
}
