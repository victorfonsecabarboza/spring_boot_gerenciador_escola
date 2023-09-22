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

import br.com.senac.dto.AlunoDTO;
import br.com.senac.entity.Aluno;
import br.com.senac.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AlunoService alunoService;

	@PostMapping
	public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
		Aluno aluno = mapper.map(alunoDTO, Aluno.class);
		aluno = alunoService.criarAluno(aluno);
		AlunoDTO alunoNovo = mapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoNovo);
	}

	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listarAlunos() {
		List<Aluno> listaAlunos = alunoService.listarTodosAlunos();
		List<AlunoDTO> listaAlunosDTO = listaAlunos.stream().map(aluno -> mapper.map(aluno, AlunoDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaAlunosDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable("id") Integer id) {
		Aluno aluno = alunoService.buscarAlunoId(id);
		AlunoDTO alunoDTO = mapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> alterarAluno(@PathVariable("id") Integer id, @RequestBody AlunoDTO alunoDTO) {
		Aluno aluno = mapper.map(alunoDTO, Aluno.class);
		aluno = alunoService.atualizarAluno(id, aluno);
		AlunoDTO alunoAlteracaoDTO = mapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoAlteracaoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarAluno(@PathVariable("id") Integer id) {
		Boolean flag = alunoService.excluirAluno(id);
		return ResponseEntity.ok().body(flag);
	}

}
