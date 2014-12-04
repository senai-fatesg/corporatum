package br.com.ambientinformatica.fatesg.corporatum.dao;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface DisciplinaDao extends Persistencia<Disciplina>{

	List<Disciplina> consultarPeloNome(String nome);
	
}
