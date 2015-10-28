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

import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component
@Path("/instituicao")
public class InstituicaoServiceControl {

	@Autowired
	public InstituicaoDao instituicaoDao;
	
	@GET
	@Path("consultarPorNome/{nomeInstituicao}")
	@Produces("text/xml")
	public List<Instituicao> listarPorNome(@PathParam("nomeInstituicao") String nomeInstituicao) {
		try {
			return instituicaoDao.consultarPeloNome(nomeInstituicao);
		} catch (NoResultException e) {
			return new ArrayList<Instituicao>();
		}
	}
	
	@GET
	@Path("listarTodos")
	@Produces("text/xml")
	public List<Instituicao> listarTodos() throws CorporatumException {
		try {
			return instituicaoDao.listar();
		} catch (PersistenciaException e) {
			throw new CorporatumException(e);
		} catch (NoResultException f) {
			return new ArrayList<Instituicao>();
		}
	}
	
}
