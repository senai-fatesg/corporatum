package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface DisciplinaDao extends Persistencia<Disciplina>{
	
	public void verificarCampos(Disciplina disciplina);

	List<Disciplina> consultarPeloNome(String nome);
	
}
