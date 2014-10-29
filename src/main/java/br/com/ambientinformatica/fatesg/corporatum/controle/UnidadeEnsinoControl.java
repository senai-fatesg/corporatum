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
import br.com.ambientinformatica.fatesg.api.UnidadeEnsino;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.UnidadeEnsinoDao;

@Controller("UnidadeEnsinoControl")
@Scope("conversation")
public class UnidadeEnsinoControl {

	private UnidadeEnsino unidadeEnsino = new UnidadeEnsino();
	
	@Autowired
	private UnidadeEnsinoDao unidadeEnsinoDao;
	
	@Autowired
	private InstituicaoDao instituicaoDao;
	
	private List<UnidadeEnsino> unidadesEnsino = new ArrayList<UnidadeEnsino>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			unidadeEnsinoDao.alterar(unidadeEnsino);
         listar(evt);
         unidadeEnsino = new UnidadeEnsino();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	public void excluir(ActionEvent evt){
		try {
			unidadeEnsino = (UnidadeEnsino) evt.getComponent().getAttributes().get("unidadeEnsino");
			unidadeEnsino = unidadeEnsinoDao.consultar(unidadeEnsino.getId());
			unidadeEnsinoDao.excluirPorId(unidadeEnsino.getId());
			listar(evt);
			
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);		
		}
	}

	public void listar(ActionEvent evt){
		try {
			unidadesEnsino = unidadeEnsinoDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}
	public List<Instituicao> consultarInstituicao(String nome){
		try {
			return instituicaoDao.listarPorNome(nome);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
			return new ArrayList<Instituicao>();
		}
	}
	

	public UnidadeEnsino getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public List<UnidadeEnsino> getUnidadesEnsino() {
		return unidadesEnsino;
	}
	

}
