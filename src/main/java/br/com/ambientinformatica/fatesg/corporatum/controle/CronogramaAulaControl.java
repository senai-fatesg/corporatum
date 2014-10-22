package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.CronogramaAula;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CronogramaAulaDao;

@Controller("CronogramaAulaControl")
@Scope("conversation")
public class CronogramaAulaControl {

	private CronogramaAula cronogramaAula  = new CronogramaAula();
	@Autowired
	private CronogramaAulaDao cronogramaAulaDao;
	
	private List<CronogramaAula> cronogramasAulas = new ArrayList<CronogramaAula>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			cronogramaAulaDao.alterar(cronogramaAula);
         listar(evt);
         cronogramaAula = new CronogramaAula();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			cronogramasAulas = cronogramaAulaDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {			
			cronogramaAulaDao.excluirPorId(cronogramaAula.getId());
			cronogramaAula = new CronogramaAula();
			cronogramasAulas = cronogramaAulaDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}	
	}
	
	public CronogramaAula getCronogramaAula() {
		return cronogramaAula;
	}

	public void setCronogramaAula(CronogramaAula cronogramaAula) {
		this.cronogramaAula = cronogramaAula;
	}

	public List<CronogramaAula> getCronogramasAulas() {
		return cronogramasAulas;
	}	
}
