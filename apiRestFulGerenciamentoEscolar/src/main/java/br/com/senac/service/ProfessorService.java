package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.Professor;
import br.com.senac.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public Professor criarProfessor(Professor professorNovo) {
		Professor professor = new Professor();
		professor.setNome(professorNovo.getNome());
		professor.setSobreNome(professorNovo.getSobreNome());
		professor.setEmail(professorNovo.getEmail());
		return professorRepository.save(professor);
	}

	public List<Professor> listarTodosProfessores() {
		return professorRepository.findAll();
	}

	public Professor buscarProfessorId(Integer id) {
		return professorRepository.findById(id).orElse(null);
	}

	public Professor atualizarProfessor(Integer id, Professor professorAlteracao) {
		Professor professor = professorRepository.findById(id).orElse(null);
		if (professor != null) {
			professor.setNome(professorAlteracao.getNome());
			professor.setSobreNome(professorAlteracao.getSobreNome());
			professor.setEmail(professorAlteracao.getEmail());
			return professorRepository.save(professor);
		} else {
			return null;
		}
	}

	public boolean excluirProfessor(Integer id) {
		Professor professor = professorRepository.findById(id).orElse(null);
		if (professor != null) {
			professorRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
