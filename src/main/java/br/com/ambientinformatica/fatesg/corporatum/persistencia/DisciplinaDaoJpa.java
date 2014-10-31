package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("disciplinaDao")
public class DisciplinaDaoJpa extends PersistenciaJpa<Disciplina> implements
		DisciplinaDao {

	private static final long serialVersionUID = 1L;
	
	@Override
	public List<Disciplina> consultarPeloNome(String nome){
		Session session = this.em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Disciplina.class);
		
		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome.toUpperCase(), MatchMode.START));
		}
		return criteria.list();
	}

}
