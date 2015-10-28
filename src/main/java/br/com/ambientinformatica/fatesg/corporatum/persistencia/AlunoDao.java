package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumStatusAluno;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface AlunoDao extends Persistencia<Aluno>{
	
	public void validarCampos(Aluno aluno) throws CorporatumException;
	
	public List<Aluno> listar(boolean todos, EnumStatusAluno status) throws CorporatumException;
	
	public Aluno consultarPorCpfCnpj(String cpfCnpj) throws CorporatumException;
	
	public List<Aluno> listarPorNome (String nome) throws CorporatumException;
	
}
