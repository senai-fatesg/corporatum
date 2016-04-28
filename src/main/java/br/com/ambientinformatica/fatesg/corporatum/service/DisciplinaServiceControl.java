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

import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;

import com.google.gson.Gson;

@Component
@Path("/disciplina")
public class DisciplinaServiceControl {

	@Autowired
	public DisciplinaDao disciplinaDao;

	@GET
	@Path("listarPorNome/{nomeDisciplina}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorNome(
			@PathParam("nomeDisciplina") String nomeDisciplina) {
		try {
			List<Disciplina> dis = disciplinaDao
					.consultarPeloNome(nomeDisciplina);

			String disciplinas = new Gson().toJson(new ArrayList<Disciplina>(dis));

			return disciplinas;
		} catch (NoResultException e) {
			return "";
		}
	}

}
