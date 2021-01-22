package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.CourseConverter;
import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    @Autowired
    private CourseConverter cConv;

    @Autowired
    private AssignmentConverter aConv;

    public List<CourseDTO> findAll() {
        List<Course> courseEntities = repo.findAll();
        return cConv.entityToDTO(courseEntities);
    }

    public CourseDTO save(CourseDTO courseDTO) {
        Course courseEntity = repo.findById(courseDTO.getId()).orElse(null);
        if (courseEntity == null) courseEntity = cConv.dtoToEntity(courseDTO);
        courseEntity = repo.save(courseEntity);
        return cConv.entityToDTO(courseEntity);
    }

    public CourseDTO getOne(int id) {
        Course courseEntity = repo.getOne(id);
        return cConv.entityToDTO(courseEntity);
    }

    public CourseDTO findById(Integer id) {
        Course courseEntity = repo.findById(id).orElse(null);
        return cConv.entityToDTO(courseEntity);
    }

    public List<CourseDTO> findCoursesByProfessorID(Integer id) {
        List<Course> courseEntities = repo.findCoursesByProfessorID(id);
        return cConv.entityToDTO(courseEntities);
    }

    public List<CourseDTO> findCoursesWhereProfessorIsNull() {
        List<Course> courseEntities = repo.findCoursesWhereProfessorIsNull();
        return cConv.entityToDTO(courseEntities);
    }

    public void delete(CourseDTO courseDTO) {
        Course courseEntity = repo.findById(courseDTO.getId()).orElse(null);
        if(courseEntity == null) return;
        repo.delete(courseEntity);
    }

    public CourseDTO update(CourseDTO courseDTO) {
        Course courseEntity = repo.findById(courseDTO.getId()).orElse(null);
        if (courseEntity == null) return null;
        courseEntity.setName(courseDTO.getName());
        courseEntity.setAssignments(aConv.dtoToEntity(courseDTO.getAssignments()));
        courseEntity = repo.save(courseEntity);
        return cConv.entityToDTO(courseEntity);
    }
}
