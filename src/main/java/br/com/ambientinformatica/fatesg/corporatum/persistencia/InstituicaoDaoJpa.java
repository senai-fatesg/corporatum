package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("instituicaoDao")
public class InstituicaoDaoJpa extends PersistenciaJpa<Instituicao> implements
		InstituicaoDao {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public List<Instituicao> consultarPeloNome(String nome) {
		String sql = "select a from Instituicao a where 1=1 ";
		if (nome != null) {
			sql += " and lower(a.nomeFantasia) like :nomeFantasia";
		}
		Query q = this.em.createQuery(sql);
		if (nome != null) {
			q.setParameter("nomeFantasia", "%" + nome.toLowerCase() + "%");
		}
		return q.getResultList();
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
