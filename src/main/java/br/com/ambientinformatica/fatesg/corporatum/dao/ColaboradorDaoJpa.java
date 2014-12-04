package br.com.ambientinformatica.fatesg.corporatum.dao;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("colaboradorDao")
public class ColaboradorDaoJpa extends PersistenciaJpa<Colaborador> implements
		ColaboradorDao {

	private static final long serialVersionUID = 1L;

	@Override
	public void verificarCampos(Colaborador colaborador) {
		String nome = colaborador.getNome();
		String rg = colaborador.getRg();
		String cpf = colaborador.getCpfCnpj();
		String tituloEleitor = colaborador.getTituloEleitor();
		String numeroReservista = colaborador.getReservista();
		String telefone = colaborador.getTelefone();
		String celular = colaborador.getCelular();
		String email = colaborador.getEmail();
		String endereco = colaborador.getEndereco();
		String municipio = colaborador.getMunicipio();
		String uf = colaborador.getUf();
		String cep = colaborador.getCep();
		String historico = colaborador.getHistorico();

		if (nome.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Nome");
		}
		if (rg.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: RG");
		}
		if (cpf.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: CPF");
		}
		if (tituloEleitor.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: titulo de eleitor");
		}
		if (numeroReservista.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: numero da reservista");
		}
		if (historico.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Histórico");
		}
		if (celular.equals("") && telefone.equals("")) {
			throw new IllegalArgumentException(
					"É necessário um numero Telefone ou Celular");
		}
		if (telefone.equals("") && celular.equals("")) {
			throw new IllegalArgumentException(
					"É necessário um numero Celular ou Telefone");
		}
		if (email.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: E-mail");
		}
		if (historico.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Histórico");
		}
		if (endereco.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Endereço");
		}
		if (municipio.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Municipio");
		}
		if (uf.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: UF(Estado)");
		}

	}

}
