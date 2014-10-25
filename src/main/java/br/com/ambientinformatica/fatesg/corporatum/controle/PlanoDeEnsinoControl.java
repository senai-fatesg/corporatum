package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
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

	public void confirmar() {
		try {
			planoDeEnsinoDao.alterar(planoDeEnsino);
			planosDeEnsino = planoDeEnsinoDao.listar();
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
	
	public void excluir() {
		try {			
			planoDeEnsinoDao.excluirPorId(planoDeEnsino.getId());
			planoDeEnsino = new PlanoDeEnsino();
			planosDeEnsino = planoDeEnsinoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}

	public void limpar(){
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

	public PlanoDeEnsinoDao getPlanoDeEnsinoDao() {
		return planoDeEnsinoDao;
	}

	public void setPlanoDeEnsinoDao(PlanoDeEnsinoDao planoDeEnsinoDao) {
		this.planoDeEnsinoDao = planoDeEnsinoDao;
	}

	public void setPlanosDeEnsino(List<PlanoDeEnsino> planosDeEnsino) {
		this.planosDeEnsino = planosDeEnsino;
	}
}
