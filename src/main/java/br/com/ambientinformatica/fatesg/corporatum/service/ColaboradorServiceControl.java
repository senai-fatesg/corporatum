package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.ArrayList;
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

import com.google.gson.Gson;

@Component
@Path("/colaborador")
public class ColaboradorServiceControl {

	@Autowired
	ColaboradorDao colaboradorDao;

	@GET
	@Path("listarPorNome/{nomeColaborador}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorNome(
			@PathParam("nomeColaborador") String nomeColaborador) {
		List<Colaborador> col = colaboradorDao.listarPorNome(nomeColaborador);

		String colaboradores = new Gson().toJson(new ArrayList<Colaborador>(col));

		return colaboradores;
	}

	@GET
	@Path("listarPorCPF/{cpfColaborador}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorCPF(
			@PathParam("cpfColaborador") String cpfColaborador) {

		Colaborador col = colaboradorDao.consultarPorCpf(cpfColaborador);

		String colaborador = new Gson().toJson(col);
		
		return colaborador; 
	}

}
