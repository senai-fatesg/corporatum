package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;

@Controller("DisciplinaControl")
@Scope("conversation")
public class DisciplinaControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Disciplina disciplina  = new Disciplina();

	@Autowired
	private DisciplinaDao disciplinaDao;	

	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();


	@PostConstruct
	public void init(){
		listar();
	}

	public void confirmar(ActionEvent evt){
		try {
			disciplinaDao.verificarCampos(disciplina);
			disciplinaDao.validar(disciplina);
			disciplinaDao.alterar(disciplina);
			UtilFaces.addMensagemFaces("Operação realizada com sucesso");
			listar();
			disciplina = new Disciplina();

		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void novaDisciplina(){
		disciplina = new Disciplina();
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.execute("PF('dlg1').show();");	
	}

	public void listar(){
		try {
			disciplinas = disciplinaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {			
			disciplinaDao.excluirPorId(disciplina.getId());
			disciplina = new Disciplina();
			listar();
			limpar();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}

	public void limpar(){
		disciplina = new Disciplina();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("disciplina.jsf");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}


	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public DisciplinaDao getDisciplinaDao() {
		return disciplinaDao;
	}	
}
