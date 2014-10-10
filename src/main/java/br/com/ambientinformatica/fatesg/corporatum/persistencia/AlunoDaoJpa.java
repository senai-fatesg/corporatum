package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Aluno;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("alunoDao")
public class AlunoDaoJpa extends PersistenciaJpa<Aluno> implements AlunoDao{

   private static final long serialVersionUID = 1L;

}
