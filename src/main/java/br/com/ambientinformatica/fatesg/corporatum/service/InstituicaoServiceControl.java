package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;

import com.thoughtworks.xstream.XStream;

@Component
@Path("/instituicao")
public class InstituicaoServiceControl {

	@Autowired
	public InstituicaoDao instituicaoDao;

	@GET
	@Path("listarPorNome/{nomeInstituicao}")
	@Produces(MediaType.APPLICATION_XML)
	public String listarPorNome(@PathParam("nomeInstituicao") String nomeInstituicao) {
		List<Instituicao> ins = instituicaoDao.consultarPeloNome(nomeInstituicao);

		return new XStream().toXML(ins);
	}
	
	@GET
	@Path("listarInstituicoes")
	@Produces(MediaType.APPLICATION_XML)
	public String listarInstituicoes() {
			List<Instituicao> instituicoes = instituicaoDao.listar();
			return new XStream().toXML(instituicoes);
	}

}
