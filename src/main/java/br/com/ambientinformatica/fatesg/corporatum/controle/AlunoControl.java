package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Aluno;
import br.com.ambientinformatica.fatesg.api.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.ValidaCPF;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl {

	private Aluno aluno = new Aluno();

	private ValidaCPF validaCPF = new ValidaCPF();

	Boolean validado = true;

	@Autowired
	private AlunoDao alunoDao;

	private EnumTipoSexo enumTipoSexo;
	// aqui vamos fornecer a lista com todos os enums
	private List<EnumTipoSexo> todosTipos;

	public List<EnumTipoSexo> getTodosTipos() {
		// aqui retornamos a lista de enums
		return Arrays.asList(EnumTipoSexo.values());
	}

	private List<Aluno> alunos = new ArrayList<Aluno>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			String cpf = aluno.getCpfCnpj();
			validado = validaCPF.validacpf(cpf);
			if (!validado == false) {
				alunoDao.alterar(aluno);
				listar(evt);
				aluno = new Aluno();
			} else {
				throw new IllegalArgumentException("CPF Inválido");
			}

		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {
			alunoDao.excluirPorId(aluno.getId());
			aluno = new Aluno();
			alunos = alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			alunos = alunoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		aluno = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

}
