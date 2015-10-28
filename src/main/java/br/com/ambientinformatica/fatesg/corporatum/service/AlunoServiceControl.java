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

import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component
@Path("/Aluno")
public class AlunoServiceControl {

	@Autowired
	public AlunoDao alunoDao;
	
	@GET
	@Path("consultarPorNome/{nomeAluno}")
	@Produces("text/xml")
	public List<Aluno> listarPorNome(@PathParam("nomeAluno") String nomeAluno) throws CorporatumException {
		try {
			return alunoDao.listarPorNome(nomeAluno);
		} catch(CorporatumException f) {
			throw new CorporatumException(f);
		} catch (NoResultException e) {
			return new ArrayList<Aluno>();
		}
	}
	
	@GET
	@Path("listarTodos")
	@Produces("text/xml")
	public List<Aluno> listarTodos() throws CorporatumException {
		try {
			return alunoDao.listar();
		} catch (PersistenciaException e) {
			throw new CorporatumException();
		} catch (NoResultException f) {
			return new ArrayList<Aluno>();
		}
	}
	
}
