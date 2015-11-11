package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;

@Component
@Path("/instituicao")
public class InstituicaoServiceControl {

	@Autowired
	public InstituicaoDao instituicaoDao;

	@POST
	@Path("listarPorNome/{nomeInstituicao}")
	@Produces("text/xml")
	public List<Instituicao> listarPorNome(
			@PathParam("nomeInstituicao") String nomeInstituicao) {
		try {
			return instituicaoDao.consultarPeloNome(nomeInstituicao);
		} catch (NoResultException e) {
			return new ArrayList<Instituicao>();
		}
	}

}
