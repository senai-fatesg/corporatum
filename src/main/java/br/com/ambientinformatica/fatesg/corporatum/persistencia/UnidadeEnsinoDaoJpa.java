package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.UnidadeEnsino;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("unidadeEnsinoDao")
public class UnidadeEnsinoDaoJpa extends PersistenciaJpa<UnidadeEnsino>
		implements UnidadeEnsinoDao {

	private static final long serialVersionUID = 1L;

	@Override
	public void verificarCampos(UnidadeEnsino unidadeEnsino) {

		String nome = unidadeEnsino.getNome();
		String telefone = unidadeEnsino.getTelefone();
		String endereco = unidadeEnsino.getEndereco();
		String sigla = unidadeEnsino.getSigla();

		if (nome.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Nome da Unidade");
		}
		if (telefone.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: telefone");
		}
		if (endereco.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Endereço da Unidade");
		}
		if (sigla.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Sigla da Unidade");
		}
	}

	@SuppressWarnings("unchecked")
	public List<UnidadeEnsino> listarPorNome(String nome) {
		Query q = this.em
				.createQuery("from UnidadeEnsino as a where a.nome like :nome");
		q.setParameter("nome", "%" + nome + "%");
		return q.getResultList();
	}

}
