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

import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;

import com.google.gson.Gson;

@Component
@Path("/aluno")
public class AlunoServiceControl {

	@Autowired
	public AlunoDao alunoDao;

	@GET
	@Path("listarPorNome/{nomeAluno}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorNome(@PathParam("nomeAluno") String nomeAluno)
			throws CorporatumException {
		List<Aluno> alu = alunoDao.listarPorNome(nomeAluno);

		String alunos = new Gson().toJson(new ArrayList<Aluno>(alu));

		return alunos;
	}

}
