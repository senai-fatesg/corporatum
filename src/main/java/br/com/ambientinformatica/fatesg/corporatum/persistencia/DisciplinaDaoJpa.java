package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("disciplinaDao")
public class DisciplinaDaoJpa extends PersistenciaJpa<Disciplina> implements DisciplinaDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> consultarPeloNome(String nome) {
		Query q = this.em.createQuery("from Disciplina as d where d.nome like :nome");
		q.setParameter("nome", "%" + nome + "%");
		return q.getResultList();
	}

}
