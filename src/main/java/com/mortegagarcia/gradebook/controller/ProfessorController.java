package com.mortegagarcia.gradebook.controller;

import com.mortegagarcia.gradebook.dto.ProfessorDTO;
import com.mortegagarcia.gradebook.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService service;

	@GetMapping("all")
	public ResponseEntity<List<ProfessorDTO>> getProfessors() {
		List<ProfessorDTO> professorsDTO = service.findAll();
		return (professorsDTO == null || professorsDTO.size() == 0) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(professorsDTO, HttpStatus.OK);
	}

	// CREATE
	@PostMapping(consumes = {"application/json"})
	public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody ProfessorDTO professor) {
		ProfessorDTO professorDTO = service.save(professor);
		return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(professorDTO, HttpStatus.OK);
	}

	// READ
	@GetMapping("{professorID}")
	public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Integer professorID) {
		ProfessorDTO professorDTO = service.findById(professorID);
		return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(professorDTO, HttpStatus.OK);
	}

	// UPDATE
	@PutMapping(path = "{{professorID}}", consumes = {"application/json"})
	public ResponseEntity<ProfessorDTO> updateProfessor(@RequestBody ProfessorDTO dto, @PathVariable Integer professorID) {
		ProfessorDTO professorDTO = service.update(professorID, dto);
		return (professorDTO == null) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(professorDTO, HttpStatus.OK);
	}

	// DELETE
	@DeleteMapping("{professorID}")
	public ResponseEntity<ProfessorDTO> deleteProfessor(@PathVariable Integer professorID) {
		ProfessorDTO deleted = service.getOne(professorID);
		if (deleted == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		service.deleteCoursesByProfessor(deleted);
		service.delete(deleted);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

}
