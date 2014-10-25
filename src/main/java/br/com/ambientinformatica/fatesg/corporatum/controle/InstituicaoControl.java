package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.fatesg.corporatum.util.ValidaCNPJ;

@Controller("InstituicaoControl")
@Scope("conversation")
public class InstituicaoControl {

	private Instituicao instituicao = new Instituicao();
	
	@Autowired
	private InstituicaoDao instituicaoDao;
	
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();
	
	private ValidaCNPJ validaCNPJ = new ValidaCNPJ();
	
	Boolean validado = true;
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			 verificarCampos();
			 
			 if(!validado.equals(false)){
				 instituicaoDao.alterar(instituicao);
				 instituicoes = instituicaoDao.listar();
		         instituicao = new Instituicao();
			 }
        
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {			
			instituicaoDao.excluirPorId(instituicao.getId());
			instituicao = new Instituicao();
			instituicoes = instituicaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}
	
	public void listar(ActionEvent evt){
		try {
			instituicoes = instituicaoDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	public void limpar(){
		instituicao = new Instituicao();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}
	public void verificarCampos(){

		String nomeFantasia = instituicao.getNomeFantasia();
		String razaoSocial = instituicao.getRazaoSocial();
		String inscricaoEstadual= instituicao.getInscricaoEstadual();
		String cnpj = instituicao.getCnpj();
		
		if(nomeFantasia.equals("")){
			throw new IllegalArgumentException("Insira Nome Fantasia");
		}
		if(razaoSocial.equals("")){
			throw new IllegalArgumentException("Insira Razão Social");
		}
		if(cnpj.equals("")){
			throw new IllegalArgumentException("Insira CNPJ");
		}else{
			validado = validaCNPJ.validaCnpj(cnpj);
			if(validado == false){
				throw new IllegalArgumentException("CNPJ Inválido");
			}
		}
		if(inscricaoEstadual.equals("")){
			throw new IllegalArgumentException("Insira Inscrição Estadual");
		}
		
	}

}
