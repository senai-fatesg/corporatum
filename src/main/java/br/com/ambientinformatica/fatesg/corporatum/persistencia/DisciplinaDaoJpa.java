package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("disciplinaDao")
public class DisciplinaDaoJpa extends PersistenciaJpa<Disciplina> implements
		DisciplinaDao {

	private static final long serialVersionUID = 1L;

	@Override
	public Disciplina consultarPorId(Long id) throws PersistenciaException {
		return em.find(Disciplina.class, id);
	}

}
