package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.corporatum.entidade.Contato;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("contatoDao")
public class ContatoDaoJpa extends PersistenciaJpa<Contato> implements ContatoDao{

   private static final long serialVersionUID = 1L;

}
