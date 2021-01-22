package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.StudentConverter;
import com.mortegagarcia.gradebook.dto.StudentDTO;
import com.mortegagarcia.gradebook.model.Student;
import com.mortegagarcia.gradebook.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    @Autowired
    private StudentConverter conv;

    public List<StudentDTO> findAll() {
        List<Student> studentEntities = repo.findAll();
        return conv.entityToDTO(studentEntities);
    }

    public StudentDTO save(StudentDTO studentDTO) {
        Student studentEntity = repo.findById(studentDTO.getId()).orElse(null);
        if (studentEntity == null) studentEntity = conv.dtoToEntity(studentDTO);
        studentEntity = repo.save(studentEntity);
        return conv.entityToDTO(studentEntity);
    }

    public StudentDTO getOne(int id) {
        Student studentEntity = repo.getOne(id);
        return conv.entityToDTO(studentEntity);
    }

    public StudentDTO findById(Integer id) {
        Student studentEntity = repo.findById(id).orElse(null);
        return conv.entityToDTO(studentEntity);
    }

    public void delete(StudentDTO studentDTO) {
        Student studentEntity = repo.findById(studentDTO.getId()).orElse(null);
        if (studentEntity == null) return;
        repo.delete(studentEntity);
    }

    public StudentDTO update(StudentDTO studentDTO) {
        Student studentEntity = repo.findById(studentDTO.getId()).orElse(null);
        if (studentEntity == null) return null;
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setGradeLevel(studentDTO.getGradeLevel());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity = repo.save(studentEntity);
        return conv.entityToDTO(studentEntity);
    }

}
