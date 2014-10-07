package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface DisciplinaDao extends Persistencia<Disciplina>{
	
	public Disciplina consultarPorId(Long codigo) throws PersistenciaException;
}
