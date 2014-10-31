package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.Date;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Matriz;
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
}
