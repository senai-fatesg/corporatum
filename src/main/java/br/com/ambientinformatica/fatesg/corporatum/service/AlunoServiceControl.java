package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;

@Component
@Path("/aluno")
public class AlunoServiceControl {

	@Autowired
	public AlunoDao alunoDao;
	
	@GET
	@Path("listarPorNome/{nomeAluno}")
	@Produces("text/xml")
	public List<Aluno> listarPorNome(@PathParam("nomeAluno") String nomeAluno) throws CorporatumException {
		return alunoDao.listarPorNome(nomeAluno);
	}
	

}
