package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.fatesg.api.PlanoDeEnsino;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.PlanoDeEnsinoDao;

@Controller("PlanoDeEnsinoControl")
@Scope("conversation")
public class PlanoDeEnsinoControl {

	private PlanoDeEnsino planoDeEnsino = new PlanoDeEnsino();

	@Autowired
	private PlanoDeEnsinoDao planoDeEnsinoDao;

	private List<PlanoDeEnsino> planosDeEnsino = new ArrayList<PlanoDeEnsino>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			planoDeEnsinoDao.alterar(planoDeEnsino);
			listar(evt);
			planoDeEnsino = new PlanoDeEnsino();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			planosDeEnsino = planoDeEnsinoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(ActionEvent evt) {
		try {
			planoDeEnsinoDao.excluirPorId(planoDeEnsino.getId());
			planoDeEnsino = new PlanoDeEnsino();
			listar(evt);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		planoDeEnsino = new PlanoDeEnsino();
	}

	public PlanoDeEnsino getPlanoDeEnsino() {
		return planoDeEnsino;
	}

	public void setPlanoDeEnsino(PlanoDeEnsino planoDeEnsino) {
		this.planoDeEnsino = planoDeEnsino;
	}

	public List<PlanoDeEnsino> getPlanosDeEnsino() {
		return planosDeEnsino;
	}

}
