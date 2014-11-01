package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Avaliacao;
import br.com.ambientinformatica.fatesg.api.EnumTipoAvaliacao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AvaliacaoDao;

@Controller("AvaliacaoControl")
@Scope("conversation")

public class AvaliacaoControl implements Serializable{

	private static final long serialVersionUID = 1L;

	private Avaliacao avaliacao = new Avaliacao();
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
	
	private EnumTipoAvaliacao tipo;
	
	@PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			if (!tipo.equals("")) {
				avaliacaoDao.alterar(avaliacao);
		         listar(evt);
		         avaliacao = new Avaliacao();
			}else{
				UtilFaces.addMensagemFaces("Selecione o tipo da avaliação");
			}
			
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {			
			avaliacaoDao.excluirPorId(avaliacao.getId());
			avaliacao = new Avaliacao();
			avaliacoes = avaliacaoDao.listar();
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

	public void limpar(){
		avaliacao = new Avaliacao();
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

	public EnumTipoAvaliacao getTipo() {
		return tipo;
	}
	public List<SelectItem> getTiposAvaliacoes() {
		return UtilFaces.getListEnum(EnumTipoAvaliacao.values());
	}
}
