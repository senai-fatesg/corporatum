package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.fatesg.api.Disciplina;

@Repository("disciplinaDao")
public class DisciplinaDaoJpa extends PersistenciaJpa<Disciplina> implements DisciplinaDao{

   private static final long serialVersionUID = 1L;

}
