package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;
import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("colaboradorDao")
public class ColaboradorDaoJpa extends PersistenciaJpa<Colaborador> implements ColaboradorDao{

   private static final long serialVersionUID = 1L;

}
