package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.fatesg.api.EnumTipoColaborador;
import br.com.ambientinformatica.fatesg.api.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;

@Controller("ColaboradorControl")
@Scope("conversation")
public class ColaboradorControl {

	private Colaborador colaborador = new Colaborador();
	
	@Autowired
	private ColaboradorDao colaboradorDao;
	
	private List<Colaborador> colaboradores = new ArrayList<Colaborador>();
	
	private EnumTipoSexo enumTipoSexo;
	//aqui vamos fornecer a lista com todos os enums
	private List<EnumTipoSexo> todosTipos;
	    
	public List<EnumTipoSexo> getTodosTipos() {
		//aqui retornamos a lista de enums
		 return Arrays.asList(EnumTipoSexo.values());	
	}
	private EnumTipoColaborador enumTipoColaborador;
	
	private List<EnumTipoColaborador> todosTiposColaborador;
	    
    public List<EnumTipoColaborador> getTodosTiposColaborador() {
		 return Arrays.asList(EnumTipoColaborador.values());
	}
    

@PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			colaboradorDao.alterar(colaborador);
         listar(evt);
         colaborador = new Colaborador();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void excluir(ActionEvent evt){
		try {			
			colaboradorDao.excluirPorId(colaborador.getId());
			colaborador = new Colaborador();
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}
	
	public void listar(ActionEvent evt){
		try {
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	public void limpar(){
		colaborador = new Colaborador();
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}
	

}
