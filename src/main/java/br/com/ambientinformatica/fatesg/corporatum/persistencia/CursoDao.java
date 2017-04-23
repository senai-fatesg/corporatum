package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

@Repository
public interface CursoDao extends Persistencia<Curso>{
	
	public void verificarCampos(Curso curso);
	
	List<Curso> listarPorNome(String nome);
}
