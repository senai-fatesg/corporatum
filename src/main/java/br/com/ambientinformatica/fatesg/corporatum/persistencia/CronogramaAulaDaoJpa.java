package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.CronogramaAula;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("cronogramaAulaDao")
public class CronogramaAulaDaoJpa extends PersistenciaJpa<CronogramaAula> implements CronogramaAulaDao{

   private static final long serialVersionUID = 1L;
   
   @Override
	public void verificarCampos(CronogramaAula cronogramaAula) {

   	String aula = cronogramaAula.getAula();
		String conteudoProgramatico = cronogramaAula.getConteudoProgramatico();
		int horasAulas = cronogramaAula.getHorasAulas();
		Disciplina disciplina = cronogramaAula.getDisciplina();
		
		if (aula.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Aula nº");
		}
		if (conteudoProgramatico.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Conteúdo Programático");
		}
		if (disciplina == null) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Disciplina");
		}
		if (horasAulas == 0) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Horas Aulas tem que ser diferente de 0");
		}
		
	}

}
