package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Matriz;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface MatrizDao extends Persistencia<Matriz> {

	public void verificarCampos(Matriz matriz);
	
	List<Matriz> consultarPeloNome(String descricao);
}
