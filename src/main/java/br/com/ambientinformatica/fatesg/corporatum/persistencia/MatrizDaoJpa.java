package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Matriz;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("matrizDao")
public class MatrizDaoJpa extends PersistenciaJpa<Matriz> implements MatrizDao{

   private static final long serialVersionUID = 1L;

}
