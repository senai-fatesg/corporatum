package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.dao.CursoDao;
import br.com.ambientinformatica.fatesg.api.dao.UnidadeEnsinoDao;
import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.entidade.EnumModalidadeCurso;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTurnoCurso;
import br.com.ambientinformatica.fatesg.api.entidade.UnidadeEnsino;

@Controller("CursoControl")
@Scope("conversation")
@ManagedBean
public class CursoControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Curso curso = new Curso();

	private UnidadeEnsino unidadeEnsino = new UnidadeEnsino();

	private CursoDao cursoDao;

	@SuppressWarnings("unused")
	private EnumTurnoCurso turnosCurso;

	private List<Curso> cursos = new ArrayList<Curso>();

	private List<UnidadeEnsino> unidadesEnsino = new ArrayList<UnidadeEnsino>();

	@Autowired
	private UnidadeEnsinoDao unidadeEnsinoDao;

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			cursoDao.verificarCampos(curso);

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

	public void limpar() {
		curso = new Curso();
	}

	public void listar(ActionEvent evt) {
		try {
			cursos = cursoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<UnidadeEnsino> completarUnidadesEnsino(String nome) {
		List<UnidadeEnsino> listaUnidadesEnsino = unidadeEnsinoDao
				.consultarPeloNome(nome);
		if (listaUnidadesEnsino.size() == 0) {
			UtilFaces
					.addMensagemFaces("UnidadeEnsino não encontrada\nVerifique o nome da UnidadeEnsino.");
		}
		return listaUnidadesEnsino;
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

	public List<UnidadeEnsino> getUnidadesEnsino() {
		return unidadesEnsino;
	}

	public void setUnidadesEnsino(List<UnidadeEnsino> unidadesEnsino) {
		this.unidadesEnsino = unidadesEnsino;
	}

	public UnidadeEnsino getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public UnidadeEnsinoDao getUnidadeEnsinoDao() {
		return unidadeEnsinoDao;
	}

	public void setUnidadeEnsinoDao(UnidadeEnsinoDao unidadeEnsinoDao) {
		this.unidadeEnsinoDao = unidadeEnsinoDao;
	}

	public List<SelectItem> getTurnosCurso() {
		return UtilFaces.getListEnum(EnumTurnoCurso.values());
	}

	public List<SelectItem> getModalidadesCurso() {
		return UtilFaces.getListEnum(EnumModalidadeCurso.values());
	}

}