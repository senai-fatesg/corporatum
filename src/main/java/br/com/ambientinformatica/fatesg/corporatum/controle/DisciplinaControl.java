package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			disciplinaDao.alterar(disciplina);
			UtilFaces.addMensagemFaces("Operação realizada com sucesso");
         listar(evt);
         disciplina = new Disciplina();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			disciplinas = disciplinaDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(ActionEvent evt) {
		try {			
			disciplinaDao.excluirPorId(disciplina.getId());
			disciplina = new Disciplina();
			listar(evt);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}
	
	public void limpar(){
		disciplina = new Disciplina();
	}
	
	public void selecionarDisciplina(ActionEvent evt) {
		try {
			 RequestContext.getCurrentInstance().closeDialog(disciplina);
			 
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
