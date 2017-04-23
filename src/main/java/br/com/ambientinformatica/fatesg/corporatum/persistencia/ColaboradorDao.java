package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

@Repository
public interface ColaboradorDao extends Persistencia<Colaborador> {

	public void verificarCampos(Colaborador colaborador);
	
	public List<Colaborador> listarPorNome(String nome);
	
	public List<Colaborador> listarPorTipo(SelectItem tipo);

	public Colaborador consultarPorCpf(String cpfColaborador);
	
	Colaborador consultarPorCpfExterno(String cpfColaborador) throws PersistenciaException;
}
