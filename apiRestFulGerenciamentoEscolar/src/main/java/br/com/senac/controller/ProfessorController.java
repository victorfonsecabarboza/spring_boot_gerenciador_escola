package br.com.senac.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.dto.ProfessorDTO;
import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProfessorService professorService;

	@PostMapping
	public ResponseEntity<ProfessorDTO> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) {
		Professor professor = mapper.map(professorDTO, Professor.class);
		professor = professorService.criarProfessor(professor);
		ProfessorDTO professorNovo = mapper.map(professor, ProfessorDTO.class);
		return ResponseEntity.ok().body(professorNovo);
	}

	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> listarProfessores() {
		List<Professor> listaProfessores = professorService.listarTodosProfessores();
		List<ProfessorDTO> listaProfessoresDTO = listaProfessores.stream().map(professor -> mapper.map(professor, ProfessorDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaProfessoresDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDTO> buscarProfessorPorId(@PathVariable("id") Integer id) {
		Professor professor = professorService.buscarProfessorId(id);
		ProfessorDTO professorDTO = mapper.map(professor, ProfessorDTO.class);
		return ResponseEntity.ok().body(professorDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDTO> alterarProfessor(@PathVariable("id") Integer id, @RequestBody ProfessorDTO professorDTO) {
		Professor professor = mapper.map(professorDTO, Professor.class);
		professor = professorService.atualizarProfessor(id, professor);
		ProfessorDTO professorAlteracaoDTO = mapper.map(professor, ProfessorDTO.class);
		return ResponseEntity.ok().body(professorAlteracaoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarProfessor(@PathVariable("id") Integer id) {
		Boolean flag = professorService.excluirProfessor(id);
		return ResponseEntity.ok().body(flag);
	}

}
