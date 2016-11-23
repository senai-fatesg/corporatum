package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;

import com.thoughtworks.xstream.XStream;

@Component
@Path("/colaborador")
public class ColaboradorServiceControl {

	@Autowired
	ColaboradorDao colaboradorDao;

	@GET
	@Path("listarPorNome/{nomeColaborador}")
	@Produces(MediaType.APPLICATION_XML)
	public String listarPorNome(@PathParam("nomeColaborador") String nomeColaborador) {
		List<Colaborador> col = colaboradorDao.listarPorNome(nomeColaborador);

		return new XStream().toXML(col);
	}

	@GET
	@Path("listarPorCPF/{cpfColaborador}")
	@Produces(MediaType.APPLICATION_XML)
	public String listarPorCPF(@PathParam("cpfColaborador") String cpfColaborador) {
		Colaborador col = colaboradorDao.consultarPorCpf(cpfColaborador);

		return new XStream().toXML(col);
	}

}
