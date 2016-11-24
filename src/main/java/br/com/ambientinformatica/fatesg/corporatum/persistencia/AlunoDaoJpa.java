package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumStatusAluno;
import br.com.ambientinformatica.fatesg.corporatum.util.CorporatumException;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilCpf;

@Repository("alunoDao")
public class AlunoDaoJpa extends PersistenciaJpa<Aluno> implements AlunoDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
   @Override
	public List<Aluno> listar(boolean todos, EnumStatusAluno status) throws CorporatumException {
		String jpaql = "select distinct a from Aluno a ";
		if(status != null){
			jpaql += " where a.status = :status";
		}
      Query query = em.createQuery(jpaql);
      if(status != null){
      	query.setParameter("status", status);
      }
      if(!todos){
      	query.setMaxResults(200);
      }
      return query.getResultList();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void validarCampos(Aluno aluno) throws CorporatumException {

		if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
			throw new CorporatumException("*Campo Obrigátorio: Nome");
		}
		if (aluno.getRg() == null || aluno.getRg().isEmpty()) {
			throw new CorporatumException("*Campo Obrigátorio: RG");
		}
		if (aluno.getCpfCnpj() == null || aluno.getCpfCnpj().isEmpty()) {
			throw new CorporatumException("*Campo Obrigátorio: CPF");
		}
		if (!UtilCpf.validarCpf(aluno.getCpfCnpj())) {
			throw new CorporatumException("*Este CPF não é válido.");
		}
		if (aluno.getTituloEleitor() == null || aluno.getTituloEleitor().isEmpty()) {
			throw new CorporatumException(
			      "*Campo Obrigátorio: titulo de eleitor");
		}
		if (aluno.getTipoSexo() == null) {
			throw new CorporatumException(
			      "*Campo Obrigátorio: Sexo");
		}
		if (aluno.getReservista() == null || aluno.getReservista().isEmpty()) {
			throw new CorporatumException(
			      "*Campo Obrigátorio: numero da reservista");
		}
		if (aluno.getCertificado2Grau() == null || aluno.getCertificado2Grau().isEmpty()) {
			throw new CorporatumException(
			      "*Campo Obrigátorio: Certificado 2º Grau");
		}
		if ((aluno.getTelefone() == null || aluno.getTelefone().isEmpty()) && (aluno.getCelular() == null || aluno.getCelular().isEmpty())) {
			throw new CorporatumException(
			      "É necessário um numero Celular ou Telefone");
		}
		if (aluno.getEmail() == null || aluno.getEmail().isEmpty()) {
			throw new CorporatumException("*Campo Obrigátorio: E-mail");
		}
		if (aluno.getCep() == null || aluno.getCep().isEmpty()) {
			throw new CorporatumException("*Campo Obrigátorio: CEP");
		}
		if (aluno.getEndereco() == null || aluno.getEndereco().isEmpty()) {
			throw new CorporatumException("*Campo Obrigátorio: Endereço");
		}
		if (aluno.getMunicipio() == null) {
			throw new CorporatumException("*Campo Obrigátorio: Municipio");
		}

	}

	@Override
   public Aluno consultarPorCpfCnpj(String cpfCnpj) throws CorporatumException {
		try{
			String jpaql = "select distinct a from Aluno a ";
			if(cpfCnpj != null){
				jpaql += " where a.cpfCnpj = :cpfCnpj";
			}else{
				throw new CorporatumException("Informe um CPF válido");
			}
			Query query = em.createQuery(jpaql);
			if(cpfCnpj != null){
				query.setParameter("cpfCnpj", cpfCnpj);
			}
			return (Aluno) query.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}
   }

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listarPorNome(String nome) throws CorporatumException {
		try{
			Query q = em.createQuery("from Aluno as a where upper(a.nome) like :nome");
			q.setParameter("nome", "%" + nome.toUpperCase() + "%" );
			return q.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Aluno>();
		}
	}

	@Override
	public List<Aluno> listar(String nomeLista, boolean todos, EnumStatusAluno status) throws CorporatumException {
		String sql = "select distinct a from Aluno a left join fetch a.municipio m where 1 = 1";
		if(status != null){
			sql += " and a.status = :status";
		}
		if(nomeLista != null && !nomeLista.isEmpty()){
			sql += " and lower(a.nome) like :nomeLista";
		}
      Query query = em.createQuery(sql);
      if(status != null){
      	query.setParameter("status", status);
      }
      if(todos){
      	query.setMaxResults(200);
      }
      if(nomeLista != null && !nomeLista.isEmpty()){
      	query.setParameter("nomeLista", "%" + nomeLista.toLowerCase() + "%");
		}
      return query.getResultList();
	}
	
	
}
