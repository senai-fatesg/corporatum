package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;

public interface ColaboradorDao extends Persistencia<Colaborador> {

	public void verificarCampos(Colaborador colaborador);
	
	List<Colaborador> listarPorNome(String nome);
	
	List<Colaborador> listarPorTipo(SelectItem tipo);

	public Colaborador consultarPorCpf(String cpfColaborador);
}
