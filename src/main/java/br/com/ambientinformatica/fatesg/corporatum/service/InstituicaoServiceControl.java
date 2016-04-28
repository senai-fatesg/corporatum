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

import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;

import com.google.gson.Gson;

@Component
@Path("/instituicao")
public class InstituicaoServiceControl {

	@Autowired
	public InstituicaoDao instituicaoDao;

	@GET
	@Path("listarPorNome/{nomeInstituicao}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorNome(
			@PathParam("nomeInstituicao") String nomeInstituicao) {
		try {
			List<Instituicao> ins = instituicaoDao
					.consultarPeloNome(nomeInstituicao);

			String instituicoes = new Gson()
					.toJson(new ArrayList<Instituicao>(ins));

			return instituicoes;
		} catch (NoResultException e) {
			return "";
		}
	}

}
