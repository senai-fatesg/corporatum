package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Avaliacao;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoAvaliacao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("avaliacaoDao")
public class AvaliacaoDaoJpa extends PersistenciaJpa<Avaliacao> implements AvaliacaoDao{

   private static final long serialVersionUID = 1L;
   
   @Override
	public  void verificarCampos(Avaliacao avaliacao) {

		String criterio = avaliacao.getCriterio();
		Disciplina disciplina = avaliacao.getDisciplina();
		EnumTipoAvaliacao tipoAvaliacao = avaliacao.getTipoAvaliacao();

		if (criterio.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Critério");
			
		}
		if (tipoAvaliacao	.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Nome da Disciplina");
		}
		
		if (disciplina == null) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Disciplina");
		}
		
	}

}
