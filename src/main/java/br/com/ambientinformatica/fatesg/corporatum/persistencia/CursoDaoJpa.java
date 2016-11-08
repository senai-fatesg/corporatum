package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;

@Repository("cursoDao")
public class CursoDaoJpa extends PersistenciaJpa<Curso> implements CursoDao {

	private static final long serialVersionUID = 1L;

	@Override
	public void verificarCampos(Curso curso) {

		String codigo = curso.getCodigo();
		String nome = curso.getNome();
		String descricao = curso.getDescricao();
		int cargaHoraria = curso.getCargaHoraria();
		String sigla = curso.getSigla();

		if (codigo.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Código do Curso");
		}
		if (nome.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Nome do Curso");
		}
		if (descricao.equals("")) {
			throw new IllegalArgumentException("*Campo Obrigátorio: Descrição");
		}
		if (cargaHoraria == 0) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Carga Horária");
		}
		if (sigla.equals("")) {
			throw new IllegalArgumentException(
					"*Campo Obrigátorio: Sigla do Curso");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarPorNome(String descricao) {
		Query q = this.em.createQuery("select c from Curso c left join fetch c.unidadeEnsino u where c.nome like :nome");
		q.setParameter("nome", "%" + descricao + "%");
		return q.getResultList();
	}
}
