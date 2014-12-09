package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.entidade.Matriz;
import br.com.ambientinformatica.fatesg.corporatum.dao.CursoDao;
import br.com.ambientinformatica.fatesg.corporatum.dao.MatrizDao;

@Controller("MatrizControl")
@Scope("conversation")
public class MatrizControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Matriz matriz = new Matriz();

	@Autowired
	private MatrizDao matrizDao;

	private List<Matriz> matrizes = new ArrayList<Matriz>();

	private String filtroGlobal = "";

	@Autowired
	private CursoDao cursoDao;

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			matrizDao.verificarCampos(matriz);
			matrizDao.alterar(matriz);
			listar(evt);
			matriz = new Matriz();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
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
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		matriz = new Matriz();
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
			UtilFaces
					.addMensagemFaces("Curso não encontrado\nVerifique o nome do Curso.");
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

}
