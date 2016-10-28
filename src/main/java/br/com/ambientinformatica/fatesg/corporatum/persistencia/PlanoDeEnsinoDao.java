package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import br.com.ambientinformatica.fatesg.api.entidade.PlanoDeEnsino;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface PlanoDeEnsinoDao extends Persistencia<PlanoDeEnsino>{
	
	public void verificarCampos(PlanoDeEnsino planoDeEnsino);

}
