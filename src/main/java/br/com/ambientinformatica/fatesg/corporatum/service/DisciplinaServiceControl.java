package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;

import com.thoughtworks.xstream.XStream;

@Component
@Path("/disciplina")
public class DisciplinaServiceControl {

	@Autowired
	public DisciplinaDao disciplinaDao;

	@GET
	@Path("listarPorNome/{nomeDisciplina}")
	@Produces(MediaType.APPLICATION_XML)
	public String listarPorNome(
			@PathParam("nomeDisciplina") String nomeDisciplina) {
			List<Disciplina> dis = disciplinaDao.consultarPeloNome(nomeDisciplina);

			return new XStream().toXML(dis);
	}
	
	@GET
	@Path("listarDisciplinas")
	@Produces(MediaType.APPLICATION_XML)
	public String listarDisciplinas() {
			List<Disciplina> disciplinas = disciplinaDao.listar();
			return new XStream().toXML(disciplinas);
	}

}
