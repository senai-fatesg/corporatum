package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("instituicaoDao")
public class InstituicaoDaoJpa extends PersistenciaJpa<Instituicao> implements InstituicaoDao{

   private static final long serialVersionUID = 1L;
   
   @SuppressWarnings("unchecked")
	@Override
	public List<Instituicao> listarPorNome(String nome) {
		Query q = this.em
				.createQuery("from Instituicao as a where a.nome like :nomefantasia");
		q.setParameter("nomefantasia", "%" + nome + "%");
		return q.getResultList();
	}


}
