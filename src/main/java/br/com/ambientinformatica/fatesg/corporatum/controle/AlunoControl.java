package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumStatusAluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.corporatum.dao.AlunoDao;
import br.com.ambientinformatica.util.UtilCpf;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno = new Aluno();

	@Autowired
	private AlunoDao alunoDao;

	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	private List<Aluno> filtrarAlunos = new ArrayList<Aluno>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			alunoDao.verificarCampos(aluno);
			String cpf = aluno.getCpfCnpj();
			if (UtilCpf.validarCpf(cpf)) {
				alunoDao.alterar(aluno);
				listar(evt);
				aluno = new Aluno();
			} else {
				UtilFaces.addMensagemFaces("CPF Inválido");
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

	public List<Aluno> getFiltrarAlunos() {
		return filtrarAlunos;
	}
	
	public void setFiltrarAlunos(List<Aluno> filtrarAlunos) {
		this.filtrarAlunos = filtrarAlunos;
	}

	public List<SelectItem> getTiposSexo() {
		return UtilFaces.getListEnum(EnumTipoSexo.values());
	}

	public List<SelectItem> getStatus() {
		return UtilFaces.getListEnum(EnumStatusAluno.values());
	}
}
