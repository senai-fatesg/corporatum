package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.api.Instituicao;

public interface InstituicaoDao extends Persistencia<Instituicao>{
	
	List<Instituicao> listarPorNome(String nome);

}
