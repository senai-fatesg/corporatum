package br.com.ambientinformatica.fatesg.corporatum.service;

import java.util.List;

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
   public List<Colaborador> listarPorNome(@PathParam("nomeColaborador") String nomeColaborador){
   	
   	List<Colaborador> colaboradores = colaboradorDao.listarPorNome(nomeColaborador.toLowerCase());
   	
   	return colaboradores;
   	
   }

}
