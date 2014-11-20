package br.com.ambientinformatica.fatesg.corporatum.dao;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.corporatum.entidade.Usuario;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("usuarioDao")
public class UsuarioDaoJpa extends PersistenciaJpa<Usuario> implements UsuarioDao{

   private static final long serialVersionUID = 1L;

}
