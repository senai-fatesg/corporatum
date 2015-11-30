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

@Component
@Path("/curso")
public class CursoServiceControl {

	@Autowired
	private CursoDao cursoDao;

	@GET
	@Path("listaPorNome/{nome}")
	@Produces("text/xml")
	public List<Curso> listaPorNome(@PathParam("nome") String nome) {
		try {
			return cursoDao.listarPorNome(nome);
		} catch (NoResultException e) {
			return new ArrayList<Curso>();
		}
	}

}
