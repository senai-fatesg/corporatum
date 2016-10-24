package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.api.entidade.PlanoDeEnsino;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("planoDeEnsinoDao")
public class PlanoDeEnsinoDaoJpa extends PersistenciaJpa<PlanoDeEnsino> implements PlanoDeEnsinoDao{

   private static final long serialVersionUID = 1L;
   
   @Override
	public void verificarCampos(PlanoDeEnsino planoDeEnsino) {

		String competencia = planoDeEnsino.getCompetencia();
		String habilidade = planoDeEnsino.getHabilidade();
		String baseTecnologica = planoDeEnsino.getBaseTecnologica();
		String metodoDeEnsino = planoDeEnsino.getMetodologiaEnsino();
		String recursosDidatico = planoDeEnsino.getRecursoDidatico();
		String bibliografia = planoDeEnsino.getBibliografia();
		

		if (competencia.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Competências");
		}
		if (habilidade.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Habilidades");
		}
		if (baseTecnologica.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Bases Tecnológicas ");
		}
		if (metodoDeEnsino.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Metod. de Ensino");
		}
		if (recursosDidatico.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Recursos Didáticos");
		}
		if (bibliografia.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Bibliografias");
		}
	
		
	}

   
}
