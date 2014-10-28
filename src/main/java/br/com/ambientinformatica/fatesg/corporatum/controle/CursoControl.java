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
import br.com.ambientinformatica.fatesg.api.Curso;
import br.com.ambientinformatica.fatesg.api.EnumModalidadeCurso;
import br.com.ambientinformatica.fatesg.api.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.api.EnumTurnoCurso;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;

@Controller("CursoControl")
@Scope("conversation")
public class CursoControl {

	private Curso curso = new Curso();
	
	@Autowired
	private CursoDao cursoDao;
	
	private List<Curso> cursos = new ArrayList<Curso>();
	
	private EnumTurnoCurso enumTurnoCurso;
	//aqui vamos fornecer a lista com todos os enums
	private List<EnumTurnoCurso> todosTipos;
	    
	public List<EnumTurnoCurso> getTodosTipos() {
		//aqui retornamos a lista de enums
		  return Arrays.asList(enumTurnoCurso.values());	
	}

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			cursoDao.alterar(curso);
         listar(evt);
         curso = new Curso();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	public void excluir() {
		try {			
			cursoDao.excluirPorId(curso.getId());
			curso = new Curso();
			cursos = cursoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}
	public void limpar(){
		curso = new Curso();
	}

	public void listar(ActionEvent evt){
		try {
			cursos = cursoDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}
	

}
