package br.com.ambientinformatica.fatesg.corporatum.dao;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface CursoDao extends Persistencia<Curso>{
	
	public void verificarCampos(Curso curso);
	
	List<Curso> listarPorNome(String nome);
}
