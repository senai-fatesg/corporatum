package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface InstituicaoDao extends Persistencia<Instituicao>{
	
	List<Instituicao> consultarPeloNome(String nomeFantasia);

	public void verificarCampos(Instituicao instituicao);

}
