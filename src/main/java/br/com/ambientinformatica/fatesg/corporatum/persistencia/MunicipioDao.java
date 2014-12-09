package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import br.com.ambientinformatica.corporativo.entidade.EnumUf;
import br.com.ambientinformatica.corporativo.entidade.Municipio;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.Persistencia;

public interface MunicipioDao extends Persistencia<Municipio>{

   List<Municipio> listarPorUf(EnumUf uf) throws PersistenciaException;
   
}
