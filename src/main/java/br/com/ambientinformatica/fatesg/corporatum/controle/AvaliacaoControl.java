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
import br.com.ambientinformatica.fatesg.api.entidade.Avaliacao;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoAvaliacao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;

@Controller("AvaliacaoControl")
@Scope("conversation")
public class AvaliacaoControl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Avaliacao avaliacao = new Avaliacao();
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;

	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

	private Disciplina disciplina = new Disciplina();

	@Autowired
	private DisciplinaDao disciplinaDao;

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {		
				avaliacaoDao.verificarCampos(avaliacao);
				avaliacaoDao.alterar(avaliacao);
				UtilFaces.addMensagemFaces("Operação realizada com sucesso");
				listar(evt);
				avaliacao = new Avaliacao();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			avaliacoes = avaliacaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {
			avaliacaoDao.excluirPorId(avaliacao.getId());
			avaliacao = new Avaliacao();
			avaliacoes = avaliacaoDao.listar();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		avaliacao = new Avaliacao();
	}

	public List<Disciplina> completarDisciplina(String nome){
		List<Disciplina> listaDisciplinas = disciplinaDao.consultarPeloNome(nome);
		if (listaDisciplinas.size() == 0) {
			UtilFaces.addMensagemFaces("Disciplina não encontrada");
		}
		return listaDisciplinas;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public List<SelectItem> getTiposAvaliacao() {
		return UtilFaces.getListEnum(EnumTipoAvaliacao.values());
	}

	public AvaliacaoDao getAvaliacaoDao() {
		return avaliacaoDao;
	}

	public DisciplinaDao getDisciplinaDao() {
		return disciplinaDao;
	}
	
	
}
