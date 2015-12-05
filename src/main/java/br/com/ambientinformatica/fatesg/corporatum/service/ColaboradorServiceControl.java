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

import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;

@Component
@Path("/colaborador")
public class ColaboradorServiceControl {

	@Autowired
	ColaboradorDao colaboradorDao;

	@GET
	@Path("listarPorNome/{nomeColaborador}")
	@Produces("text/xml")
	public List<Colaborador> listarPorNome(
			@PathParam("nomeColaborador") String nomeColaborador) {
		try {
			return colaboradorDao.listarPorNome(nomeColaborador);
		} catch (NoResultException e) {
			return new ArrayList<Colaborador>();
		}
	}

	@GET
	@Path("listarPorCPF/{cpfColaborador}")
	@Produces("text/xml")
	public Colaborador listarPorCPF(
			@PathParam("cpfColaborador") String cpfColaborador) {

		Colaborador colaborador = new Colaborador();
		colaborador = colaboradorDao.consultarPorCpf(cpfColaborador);

		return colaborador;

	}

}
