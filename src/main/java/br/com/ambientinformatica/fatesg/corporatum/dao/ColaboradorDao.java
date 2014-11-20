package br.com.ambientinformatica.fatesg.corporatum.dao;

import br.com.ambientinformatica.jpa.persistencia.Persistencia;
import br.com.ambientinformatica.fatesg.api.Colaborador;

public interface ColaboradorDao extends Persistencia<Colaborador> {

	public void verificarCampos(Colaborador colaborador);
}
