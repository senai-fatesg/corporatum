package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.api.entidade.UnidadeEnsino;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.UnidadeEnsinoDao;

@Controller("UnidadeEnsinoControl")
@Scope("conversation")
public class UnidadeEnsinoControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private UnidadeEnsino unidadeEnsino = new UnidadeEnsino();

	@Autowired
	private UnidadeEnsinoDao unidadeEnsinoDao;

	private Instituicao instituicao = new Instituicao();

	@Autowired
	private InstituicaoDao instituicaoDao;

	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();

	private List<UnidadeEnsino> unidadesEnsino = new ArrayList<UnidadeEnsino>();

	private String filtroGlobal = "";

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {

			unidadeEnsinoDao.verificarCampos(unidadeEnsino);
			unidadeEnsinoDao.alterar(unidadeEnsino);
			listar(evt);
			unidadeEnsino = new UnidadeEnsino();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(ActionEvent evt) {
		try {
			unidadeEnsinoDao.excluirPorId(unidadeEnsino.getId());
			unidadeEnsino = new UnidadeEnsino();
			unidadesEnsino = unidadeEnsinoDao.listar();
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Erro ao excluir, provavelmente tem um Curso vinculado a esta unidade "
					+ "de ensino. ");
			
		}
	}

	public void listar(ActionEvent evt) {
		try {
			unidadesEnsino = unidadeEnsinoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Instituicao> completarInstituicoes(String nomefantasia) {
		List<Instituicao> listaInstitucoes = instituicaoDao
				.consultarPeloNome(nomefantasia);
		if (listaInstitucoes.size() == 0) {
			UtilFaces
					.addMensagemFaces("Instituição não encontrada\nVerifique o nome da Instituição.");
		}
		return listaInstitucoes;
	}

	public void filtrarPorNome() {
		try {
			unidadesEnsino = unidadeEnsinoDao.listarPorNome(filtroGlobal);
			if (unidadesEnsino.isEmpty()) {
				unidadesEnsino = unidadeEnsinoDao.listarPorNome(filtroGlobal);
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limparConsulta() {
		filtroGlobal = "";
		try {
			unidadesEnsino = unidadeEnsinoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		unidadeEnsino = new UnidadeEnsino();
	}

	public UnidadeEnsino getUnidadeEnsino() {
		return unidadeEnsino;
	}

	public void setUnidadeEnsino(UnidadeEnsino unidadeEnsino) {
		this.unidadeEnsino = unidadeEnsino;
	}

	public List<UnidadeEnsino> getUnidadesEnsino() {
		return unidadesEnsino;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public UnidadeEnsinoDao getUnidadeEnsinoDao() {
		return unidadeEnsinoDao;
	}

	public String getFiltroGlobal() {
		return filtroGlobal;
	}

	public void setFiltroGlobal(String filtroGlobal) {
		this.filtroGlobal = filtroGlobal;
	}

}
