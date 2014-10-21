package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;

@Controller("DisciplinaControl")
@Scope("conversation")
public class DisciplinaControl {

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
