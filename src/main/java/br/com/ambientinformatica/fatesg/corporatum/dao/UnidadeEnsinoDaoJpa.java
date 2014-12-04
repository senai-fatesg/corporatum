package br.com.ambientinformatica.fatesg.corporatum.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.UnidadeEnsino;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("unidadeEnsinoDao")
public class UnidadeEnsinoDaoJpa extends PersistenciaJpa<UnidadeEnsino> implements UnidadeEnsinoDao{

   private static final long serialVersionUID = 1L;
     
   @Override
	public void verificarCampos(UnidadeEnsino unidadeEnsino) {

		String nome = unidadeEnsino.getNome();
		String telefone = unidadeEnsino.getTelefone();
		String endereco = unidadeEnsino.getEndereco();
		String sigla = unidadeEnsino.getSigla();
		
		if (nome.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Nome da Unidade");
		}
		if (telefone.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: telefone");
		}
		if (endereco.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Endereço da Unidade");
		}
		if (sigla.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Sigla da Unidade");
		}
	}

@SuppressWarnings("unchecked")
@Override
public List<UnidadeEnsino> consultarPeloNome(String nome) {
	Session session = this.em.unwrap(Session.class);
	Criteria criteria = session.createCriteria(UnidadeEnsino.class);
	
	if (StringUtils.isNotBlank(nome)) {
		criteria.add(Restrictions.ilike("nome", nome.toUpperCase(), MatchMode.START));
	}
		return criteria.list();
}

}
