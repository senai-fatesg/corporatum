package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component
@Path("/curso")
public class CursoServiceControl {

	@Autowired
	private CursoDao cursoDao;

	@GET
	@Path("consultarPorNome/{nome}")
	@Produces("text/xml")
	public List<Curso> listaPorDescricao(@PathParam("nome") String nome) {
		try {
			return cursoDao.listarPorDescricao(nome);
		} catch (NoResultException e) {
			return new ArrayList<Curso>();
		}
	}

	@GET
	@Path("ListarTodos")
	@Produces("text/xml")
	public List<Curso> listarTodos() throws CorporatumException {
		List<Curso> cursos;
		try {
			cursos = cursoDao.listar();
		} catch (PersistenciaException e) {
			throw new CorporatumException(e);
		}
		return cursos;
	}

}
