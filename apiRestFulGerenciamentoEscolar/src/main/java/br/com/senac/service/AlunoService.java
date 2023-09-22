package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Aluno;
import br.com.senac.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno criarAluno(Aluno alunoNovo) {
		Aluno aluno = new Aluno();
		aluno.setNome(alunoNovo.getNome());
		aluno.setSobreNome(alunoNovo.getSobreNome());
		aluno.setEmail(alunoNovo.getEmail());
		return alunoRepository.save(aluno);
	}

	public List<Aluno> listarTodosAlunos() {
		return alunoRepository.findAll();
	}

	public Aluno buscarAlunoId(Integer id) {
		return alunoRepository.findById(id).orElse(null);
	}

	public Aluno atualizarAluno(Integer id, Aluno alunoAlteracao) {
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		if (aluno != null) {
			aluno.setNome(alunoAlteracao.getNome());
			aluno.setSobreNome(alunoAlteracao.getSobreNome());
			aluno.setEmail(alunoAlteracao.getEmail());
			return alunoRepository.save(aluno);
		} else {
			return null;
		}
	}

	public boolean excluirAluno(Integer id) {
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		if (aluno != null) {
			alunoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
