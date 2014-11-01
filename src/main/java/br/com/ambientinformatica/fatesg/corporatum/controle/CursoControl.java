package br.com.ambientinformatica.fatesg.corporatum.controle;

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
import br.com.ambientinformatica.fatesg.api.Curso;
import br.com.ambientinformatica.fatesg.api.EnumTurnoCurso;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;

@Controller("CursoControl")
@Scope("conversation")
@ManagedBean
public class CursoControl {

	private Curso curso = new Curso();

	@Autowired
	private CursoDao cursoDao;

	@SuppressWarnings("unused")
	private EnumTurnoCurso turnosCurso;

	private List<Curso> cursos = new ArrayList<Curso>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public List<SelectItem> getTurnosCurso() {
		return UtilFaces.getListEnum(EnumTurnoCurso.values());
	}

	public void confirmar(ActionEvent evt) {
		try {
			EnumTurnoCurso turnoCurso = curso.getTurno();
			if (!turnoCurso.equals("")) {
				cursoDao.alterar(curso);
				listar(evt);
				curso = new Curso();
			} else {
				UtilFaces.addMensagemFaces("Selecione turno do Curso!");
			}

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