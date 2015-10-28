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

import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component
@Path("/disciplina")
public class DisciplinaServiceControl {

	@Autowired
	public DisciplinaDao disciplinaDao;
	
	@GET
	@Path("consultarPorNome/{nomeDisciplina}")
	@Produces("text/xml")
	public List<Disciplina> listarPorNome(@PathParam("nomeDisciplina") String nomeDisciplina) {
		try {
			return disciplinaDao.consultarPeloNome(nomeDisciplina);
		} catch (NoResultException e) {
			return new ArrayList<Disciplina>();
		}
	}
	
	@GET
	@Path("listarTodos")
	@Produces("text/xml")
	public List<Disciplina> listarTodos() throws CorporatumException {
		try {
			return disciplinaDao.listar();
		} catch (PersistenciaException e) {
			throw new CorporatumException(e);
		} catch (NoResultException f) {
			return new ArrayList<Disciplina>();
		}
	}
	
}
