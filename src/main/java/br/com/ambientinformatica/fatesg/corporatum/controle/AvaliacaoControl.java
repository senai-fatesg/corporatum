package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Avaliacao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AvaliacaoDao;

@Controller("AvaliacaoControl")
@Scope("conversation")
public class AvaliacaoControl {

	private Avaliacao avaliacao = new Avaliacao();
	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			avaliacaoDao.alterar(avaliacao);
         listar(evt);
         avaliacao = new Avaliacao();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			avaliacoes = avaliacaoDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}	
}
