package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.UnidadeEnsino;
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

}
