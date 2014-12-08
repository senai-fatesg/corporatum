package br.com.ambientinformatica.fatesg.corporatum.dao;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumStatusAluno;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AlunoDao extends Persistencia<Aluno>{
	
	public void verificarCampos(Aluno aluno);
	
	public List<Aluno> consultarPeloStatus(EnumStatusAluno status);
	
}
