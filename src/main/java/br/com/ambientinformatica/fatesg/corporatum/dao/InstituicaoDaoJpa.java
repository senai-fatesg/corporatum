package br.com.ambientinformatica.fatesg.corporatum.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("instituicaoDao")
public class InstituicaoDaoJpa extends PersistenciaJpa<Instituicao> implements
		InstituicaoDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Instituicao> consultarPeloNome(String nomeFantasia){
		Session session = this.em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Instituicao.class);
		
		if (StringUtils.isNotBlank(nomeFantasia)) {
			criteria.add(Restrictions.ilike("nomeFantasia", nomeFantasia.toUpperCase(), MatchMode.START));
		}
		return criteria.list();
	}
	@Override
	public void verificarCampos(Instituicao instituicao) {

		String nomeFantasia = instituicao.getNomeFantasia();
		String razaoSocial = instituicao.getRazaoSocial();
		String inscricaoEstadual = instituicao.getInscricaoEstadual();
		String cnpj = instituicao.getCnpj();

		if (nomeFantasia.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Nome Fantasia");
		}
		if (razaoSocial.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Razão Social");
		}
		if (cnpj.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: CNPJ");
		}
		if (inscricaoEstadual.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Inscrição Estadual");
		}

	}
}
