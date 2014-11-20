package br.com.ambientinformatica.fatesg.corporatum.dao;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.api.Aluno;

public interface AlunoDao extends Persistencia<Aluno>{
	
	public void verificarCampos(Aluno aluno);

}
