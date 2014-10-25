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

@Controller("InstituicaoControl")
@Scope("conversation")
public class InstituicaoControl {

	private Instituicao instituicao = new Instituicao();
	
	@Autowired
	private InstituicaoDao instituicaoDao;
	
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			instituicaoDao.alterar(instituicao);
         listar(evt);
         instituicao = new Instituicao();
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
	

}
