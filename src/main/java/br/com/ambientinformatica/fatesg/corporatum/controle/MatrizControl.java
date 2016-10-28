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
import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.api.entidade.Matriz;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.MatrizDao;

@Controller("MatrizControl")
@Scope("conversation")
public class MatrizControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Matriz matriz = new Matriz();

	private Disciplina disciplina;

	@Autowired
	private MatrizDao matrizDao;

	private String filtroGlobal = "";

	private List<Matriz> matrizes = new ArrayList<Matriz>();

	private List<Disciplina> disciplinas = new ArrayList<>();

	@Autowired
	private CursoDao cursoDao;

	@Autowired
	private DisciplinaDao disciplinaDao;

	@PostConstruct
	public void init() {
		listar();
	}

	public void listar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("matriz", new Matriz());
			matrizes = matrizDao.listar();
			disciplinas =  disciplinaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void confirmar(ActionEvent evt) {
		try {
			matrizDao.verificarCampos(matriz);
			matrizDao.alterar(matriz);
			matriz = new Matriz();
			listar();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}


	public void excluir() {
		try {
			matrizDao.excluirPorId(matriz.getId());
			matriz = new Matriz();
			matrizes = matrizDao.listar();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		matriz = new Matriz();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("matrizLista.jsf");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void novoMatriz(){
		matriz = new Matriz();
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.execute("PF('dlgMatriz').show();");	
	}

	public void adicionarDisciplina(){
		matriz.add(disciplina);
	}

	public void removerDisciplina(Disciplina parametro){
		matriz.remover(parametro);
		UtilFaces.addMensagemFaces("Item removido!");
	}

	public void filtrarPorNome() {
		try {
			matrizes = matrizDao.consultarPeloNome(filtroGlobal);
			if (matrizes.isEmpty()) {
				matrizes = matrizDao.consultarPeloNome(filtroGlobal);
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limparConsulta() {
		filtroGlobal = "";
		try {
			matrizes = matrizDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Curso> completarCursos(String nome) {
		List<Curso> listaCursos = cursoDao.listarPorNome(nome);
		if (listaCursos.size() == 0) {
			UtilFaces.addMensagemFaces("Curso não encontrado\nVerifique o nome do Curso.");
		}
		return listaCursos;
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

	public String getFiltroGlobal() {
		return filtroGlobal;
	}

	public void setFiltroGlobal(String filtroGlobal) {
		this.filtroGlobal = filtroGlobal;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
