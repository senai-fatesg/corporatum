package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.PlanoDeEnsino;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("planoDeEnsinoDao")
public class PlanoDeEnsinoDaoJpa extends PersistenciaJpa<PlanoDeEnsino> implements PlanoDeEnsinoDao{

   private static final long serialVersionUID = 1L;

}
