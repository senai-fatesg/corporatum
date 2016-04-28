package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;

@Component
@Path("/curso")
public class CursoServiceControl {

	@Autowired
	private CursoDao cursoDao;

	@GET
	@Path("listaPorNome/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listaPorNome(@PathParam("nome") String nome) {
		try {
			List<Curso> cur = cursoDao.listarPorNome(nome);

			String cursos = new Gson().toJson(new ArrayList<Curso>(cur));

			return cursos;
		} catch (NoResultException e) {
			return "";
		}
	}

}
