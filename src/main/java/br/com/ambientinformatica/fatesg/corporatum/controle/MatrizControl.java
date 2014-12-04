package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.dao.CursoDao;
import br.com.ambientinformatica.fatesg.api.dao.MatrizDao;
import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.api.entidade.Matriz;

@Controller("MatrizControl")
@Scope("conversation")
public class MatrizControl {

	private Matriz matriz = new Matriz();

	@Autowired
	private MatrizDao matrizDao;
	
	private List<Matriz> matrizes = new ArrayList<Matriz>();
	
	private List<Curso> cursos = new ArrayList<Curso>();
	
	@Autowired
	private CursoDao cursoDao;
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			matrizDao.verificarCampos(matriz);
			matrizDao.alterar(matriz);
         listar(evt);
         matriz = new Matriz();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			matrizes = matrizDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void excluir() {
		try {			
			matrizDao.excluirPorId(matriz.getId());
			matriz = new Matriz();
			matrizes = matrizDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}
	public List<Curso> completarCursos(String nome) {
		List<Curso> listaCursos = cursoDao.consultarPeloNome(nome);
		if (listaCursos.size() == 0) {
			UtilFaces.addMensagemFaces("Curso não encontrado\nVerifique o nome do Curso.");
		}
		return listaCursos;
	}
	public void limpar(){
		matriz = new Matriz();
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public List<Matriz> getMatrizes() {
		return matrizes;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
}
