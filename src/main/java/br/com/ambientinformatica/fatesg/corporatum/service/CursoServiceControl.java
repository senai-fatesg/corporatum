package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;

@Component
@Path("/curso")
public class CursoServiceControl {

	@Autowired
	private CursoDao cursoDao;

	@GET
	@Path("listaPorNome/{nome}")
	@Produces(MediaType.APPLICATION_XML)
	public String listaPorNome(@PathParam("nome") String nome) {
			List<Curso> cur = cursoDao.listarPorNome(nome);

			return new XStream().toXML(cur);
		
	}

}
