package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

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
		if (cep.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: CEP");
		}
		if (endereco.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Endereço");
		}

	}

	
	@SuppressWarnings("unchecked")
	public List<Colaborador> listarPorNome(String nome) throws PersistenciaException{
		try {
			String sql = "select distinct c from Colaborador c where 1=1 ";
			if (nome != null && !nome.isEmpty()) {
				sql += " and upper (c.nome) like upper (:nome) ";
			}
			
			sql += " order by c.nome";
			Query query = em.createQuery(sql);
			
			if (nome != null && !nome.isEmpty()) {
				query.setParameter("nome", "%" + nome + "%");
			}
			return query.getResultList();
		} catch (Exception e) {
			UtilLog.getLog().error(e.getMessage(), e);
			throw new PersistenciaException("Erro ao consultar", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colaborador> listarPorTipo(SelectItem tipo) {
		Query query = em.createQuery("from Colaborador as a where a.tipo like :tipo");
		query.setParameter("tipo", "%" + tipo + "%");
		return query.getResultList();
	}

	@Override
   public Colaborador consultarPorCpf(String cpfColaborador) {
		Query query = em.createQuery("from Colaborador a where a.cpfCnpj like :cpfColaborador");
		query.setParameter("cpfColaborador", cpfColaborador);
		return (Colaborador) query.getSingleResult();
   }
	
	public Colaborador consultarPorCpfExterno(String cpfColaborador) throws PersistenciaException{
		try {
			Query query = em.createQuery("select c from Colaborador c left join fetch c.municipio m WHERE c.cpfCnpj = :cpf");
			query.setParameter("cpf", cpfColaborador);
			query.setMaxResults(1);
			List<Colaborador> colaboradores = query.getResultList();
			if (colaboradores.isEmpty()) {
				return null;
			}else {
				return colaboradores.get(0);
			}
		} catch (Exception e) {
			UtilLog.getLog().error(e.getMessage(), e);
			throw new PersistenciaException("Erro ao consultar colaborador por cpf", e);
		}
	}
		
}
