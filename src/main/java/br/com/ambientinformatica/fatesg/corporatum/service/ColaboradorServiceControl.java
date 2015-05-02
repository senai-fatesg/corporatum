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
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component
@Path("/colaborador")
public class ColaboradorServiceControl {

	@Autowired
   ColaboradorDao colaboradorDao;
   
   @GET
	@Path("consultarPorCpf/{cpfColaborador}")
	@Produces("text/xml")
   public Colaborador listarPorNome(@PathParam("cpfColaborador") String cpfColaborador){
   	
   	Colaborador colaborador = colaboradorDao.consultarPorCpf(cpfColaborador);
   	
   	return colaborador;
   	
   }
   
   @GET
	@Path("listarTodos")
	@Produces("text/xml")
   public List<Colaborador> listarTodos() throws CorporatumException{
   	List<Colaborador> colaboradores;
      try {
	      colaboradores = colaboradorDao.listar();
      } catch (PersistenciaException e) {
	      throw new CorporatumException(e);
      }
   	
   	return colaboradores;
   	
   }

}
