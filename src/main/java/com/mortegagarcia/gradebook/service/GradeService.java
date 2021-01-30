package com.mortegagarcia.gradebook.service;

import com.mortegagarcia.gradebook.converter.GradeConverter;
import com.mortegagarcia.gradebook.dto.GradeDTO;
import com.mortegagarcia.gradebook.model.Assignment;
import com.mortegagarcia.gradebook.model.Grade;
import com.mortegagarcia.gradebook.model.Student;
import com.mortegagarcia.gradebook.repository.AssignmentRepository;
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
		Grade gradeEntity = saveNewGrade(gradeDTO);
		return gConv.entityToDTO(gradeEntity);
	}

	public GradeDTO getOne(int id) {
		Grade gradeEntity = gradeRepository.getOne(id);
		return gConv.entityToDTO(gradeEntity);
	}

	public GradeDTO findById(Integer id) {
		Grade gradeEntity = gradeRepository.findById(id)
				.orElseThrow(IllegalArgumentException::new);
		return gConv.entityToDTO(gradeEntity);
	}

	public void delete(GradeDTO gradeDTO) {
		Grade gradeEntity = gradeRepository.findById(gradeDTO.getId())
				.orElseThrow(IllegalArgumentException::new);
		gradeRepository.delete(gradeEntity);
	}

	public GradeDTO update(Integer gradeId, GradeDTO dto) {
		Grade gradeEntity = gradeRepository.findById(gradeId).orElse(null);
		if (gradeEntity == null) gradeEntity = saveNewGrade(dto);
		else gradeEntity = saveExistingGrade(gradeEntity, dto);
		return gConv.entityToDTO(gradeEntity);
	}

	private Grade saveExistingGrade(Grade gradeEntity, GradeDTO gradeDTO) {
		gradeEntity.setScore(gradeDTO.getScore());
		if (gradeEntity.getStudent().getId() != gradeDTO.getStudentID()) {
			Student studentEntity = studentRepository.findById(gradeDTO.getStudentID())
					.orElseThrow(IllegalArgumentException::new);
			gradeEntity.setStudent(studentEntity);
		}
		if (gradeEntity.getAssignment().getId() != gradeDTO.getAssignmentID()) {
			Assignment assignmentEntity = assignmentRepository.findById(gradeDTO.getAssignmentID())
					.orElseThrow(IllegalArgumentException::new);
			gradeEntity.setAssignment(assignmentEntity);
		}
		return gradeEntity;
	}

	private Grade saveNewGrade(GradeDTO gradeDTO) {
		Grade gradeEntity = gConv.dtoToEntity(gradeDTO);
		Student studentEntity = studentRepository.findById(gradeDTO.getStudentID())
				.orElseThrow(IllegalArgumentException::new);
		gradeEntity.setStudent(studentEntity);
		Assignment assignmentEntity = assignmentRepository.findById(gradeDTO.getAssignmentID())
				.orElseThrow(IllegalArgumentException::new);
		gradeEntity.setAssignment(assignmentEntity);
		gradeEntity = gradeRepository.save(gradeEntity);
		return gradeEntity;
	}

}
