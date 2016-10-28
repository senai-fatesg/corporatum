package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import br.com.ambientinformatica.fatesg.api.entidade.Avaliacao;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AvaliacaoDao extends Persistencia<Avaliacao>{
	
	public void verificarCampos(Avaliacao avaliacao);

}
