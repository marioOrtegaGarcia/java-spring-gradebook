package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.AssignmentConverter;
import com.mortegagarcia.gradebook.converter.CourseConverter;
import com.mortegagarcia.gradebook.dto.AssignmentDTO;
import com.mortegagarcia.gradebook.dto.CourseDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Course;
import com.mortegagarcia.gradebook.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseConverter cConv;

    @Autowired
    private AssignmentConverter aConv;

    public List<CourseDTO> findAll() {
        List<Course> courseEntities = courseRepository.findAll();
        return cConv.entityToDTO(courseEntities);
    }

    public CourseDTO save(CourseDTO courseDTO) {
        Course courseEntity = courseRepository.findById(courseDTO.getId()).orElse(null);
        if (courseEntity == null) courseEntity = cConv.dtoToEntity(courseDTO);
        courseEntity = courseRepository.save(courseEntity);
        return cConv.entityToDTO(courseEntity);
    }

    public CourseDTO getOne(int id) {
        Course courseEntity = courseRepository.getOne(id);
        return cConv.entityToDTO(courseEntity);
    }

    public CourseDTO findById(Integer id) {
        Course courseEntity = courseRepository.findById(id).orElse(null);
        return cConv.entityToDTO(courseEntity);
    }

    public List<CourseDTO> findCoursesByProfessorID(Integer id) {
        List<Course> courseEntities = courseRepository.findCoursesByProfessorID(id);
        return cConv.entityToDTO(courseEntities);
    }

    public List<CourseDTO> findCoursesWhereProfessorIsNull() {
        List<Course> courseEntities = courseRepository.findCoursesWhereProfessorIsNull();
        return cConv.entityToDTO(courseEntities);
    }

    public void delete(CourseDTO courseDTO) {
        Course courseEntity = courseRepository.findById(courseDTO.getId()).orElse(null);
        if(courseEntity == null) return;
        courseRepository.delete(courseEntity);
    }

    public CourseDTO update(CourseDTO courseDTO) {
        Course courseEntity = courseRepository.findById(courseDTO.getId()).orElse(null);
        if (courseEntity == null) return null;
        courseEntity.setName(courseDTO.getName());
        courseEntity.setAssignments(aConv.dtoToEntity(courseDTO.getAssignments()));
        courseEntity = courseRepository.save(courseEntity);
        return cConv.entityToDTO(courseEntity);
    }

    public AssignmentDTO findCourseAssignment(Integer courseID, Integer assignmentID) {
        Assignment assignment = courseRepository.findCourseAssignment(courseID, assignmentID);
        return aConv.entityToDTO(assignment);
    }

    public List<AssignmentDTO> getCourseAssignments(Integer courseID) {
        List<Assignment> assignments = courseRepository.getCourseAssignments(courseID);
        return aConv.entityToDTO(assignments);
    }

    public List<AssignmentDTO> deleteCourseAssignments(Integer courseID) {
        List<AssignmentDTO> assignmentDTOS = aConv.entityToDTO(courseRepository.getCourseAssignments(courseID));
        courseRepository.deleteCourseAssignments(courseID);
        return assignmentDTOS;
    }
}
