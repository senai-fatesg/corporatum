package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("instituicaoDao")
public class InstituicaoDaoJpa extends PersistenciaJpa<Instituicao> implements
		InstituicaoDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Instituicao> listarPorNome(String nome) {
		
			Query query = em.createQuery("select instituicao from Instituicao instituicao where upper(instituicao.nomefantasia) like :nome");
			query.setParameter("nome", "%"+nome.toUpperCase()+"%") ;
			return query.getResultList();
	}
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Instituicao> listarPorNome(String nomefantasia) {
		Query q = this.em
				.createQuery("from instituicao as a where a.nomefantasia like :nomefantasia");
		q.setParameter("nomefantasia", "%" + nomefantasia + "%");
		return q.getResultList();
	}*/

	@Override
	public void verificarCampos(Instituicao instituicao) {

		String nomeFantasia = instituicao.getNomeFantasia();
		String razaoSocial = instituicao.getRazaoSocial();
		String inscricaoEstadual = instituicao.getInscricaoEstadual();
		String cnpj = instituicao.getCnpj();

		if (nomeFantasia.equals("")) {
			throw new IllegalArgumentException("Insira Nome Fantasia");
		}
		if (razaoSocial.equals("")) {
			throw new IllegalArgumentException("Insira Razão Social");
		}
		if (cnpj.equals("")) {
			throw new IllegalArgumentException("Insira CNPJ");
		}
		if (inscricaoEstadual.equals("")) {
			throw new IllegalArgumentException("Insira Inscrição Estadual");
		}

	}
}
