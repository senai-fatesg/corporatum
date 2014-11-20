package br.com.ambientinformatica.fatesg.corporatum.dao;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Avaliacao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("avaliacaoDao")
public class AvaliacaoDaoJpa extends PersistenciaJpa<Avaliacao> implements AvaliacaoDao{

   private static final long serialVersionUID = 1L;

}
