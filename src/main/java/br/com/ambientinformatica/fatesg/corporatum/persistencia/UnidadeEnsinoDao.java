package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.UnidadeEnsino;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface UnidadeEnsinoDao extends Persistencia<UnidadeEnsino> {
	
	List<UnidadeEnsino> consultarPeloNome(String nome);

	public void verificarCampos(UnidadeEnsino unidadeEnsino);
}
