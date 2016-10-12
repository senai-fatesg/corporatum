package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("disciplinaDao")
public class DisciplinaDaoJpa extends PersistenciaJpa<Disciplina> implements DisciplinaDao {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void verificarCampos(Disciplina disciplina) {

		String codigo = disciplina.getCodigo();
		String nome = disciplina.getNome();
		int cargaHoraria = disciplina.getCargaHoraria();

		if (codigo.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Código da Disciplina");
		}
		if (nome.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Nome da Disciplina");
		}
		
		if (cargaHoraria == 0) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Carga Horária tem que ser diferente de 0");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> consultarPeloNome(String nome) {
		Query q = this.em.createQuery("from Disciplina as d where d.nome like :nome");
		q.setParameter("nome", "%" + nome + "%");
		return q.getResultList();
	}

}
