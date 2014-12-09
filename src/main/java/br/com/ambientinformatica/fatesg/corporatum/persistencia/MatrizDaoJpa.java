package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Matriz;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("matrizDao")
public class MatrizDaoJpa extends PersistenciaJpa<Matriz> implements MatrizDao {

	private static final long serialVersionUID = 1L;

	@Override
	public void verificarCampos(Matriz matriz) {

		String descricao = matriz.getDescricao();
		Date date = matriz.getData();
		int qtdPeriodos = matriz.getQtdPeriodos();

		if (descricao.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Descrição");
		}
		if (date.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Data");
		}
		if (qtdPeriodos == 0) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Quantitade de Periodo");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matriz> consultarPeloNome(String descricao) {
		Query q = this.em
				.createQuery("from Matriz as a where a.descricao like :descricao");
		q.setParameter("descricao", "%" + descricao + "%");
		return q.getResultList();
	}
}
