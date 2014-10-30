package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.Aluno;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("alunoDao")
public class AlunoDaoJpa extends PersistenciaJpa<Aluno> implements AlunoDao {

	private static final long serialVersionUID = 1L;

	@Override
	public void verificarCampos(Aluno aluno) {

		String nome = aluno.getNome();
		String rg = aluno.getRg();
		String cpf = aluno.getCpfCnpj();
		String tituloEleitor = aluno.getTituloEleitor();
		String numeroReservista = aluno.getReservista();
		String telefone = aluno.getTelefone();
		String celular = aluno.getCelular();
		String email = aluno.getEmail();
		String endereco = aluno.getEndereco();
		String municipio = aluno.getMunicipio();
		String uf = aluno.getUf();
		String cep = aluno.getCep();
		String certificado2Grau = aluno.getCertificado2Grau();

		if (nome.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Nome");
		}
		if (rg.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: RG");
		}
		if (cpf.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: CPF");
		}
		if (tituloEleitor.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: titulo de eleitor");
		}
		if (numeroReservista.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: numero da reservista");
		}
		if (celular.equals("")&& telefone.equals("")) {
			throw new IllegalArgumentException(
					"É necessário um numero Telefone ou Celular");
		}
		if (telefone.equals("") && celular.equals("")) {
			throw new IllegalArgumentException(
					"É necessário um numero Celular ou Telefone");
		}
		if (email.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: E-mail");
		}
		if(endereco.equals("")){
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Endereço");
		}
		if(municipio.equals("")){
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Municipio");
		}
		if(uf.equals("")){
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: UF(Estado)");
		}
		if(cep.equals("")){
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: CEP");
		}
		if(certificado2Grau.equals("")){
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Certificado 2º Grau");
		}
	}

}
