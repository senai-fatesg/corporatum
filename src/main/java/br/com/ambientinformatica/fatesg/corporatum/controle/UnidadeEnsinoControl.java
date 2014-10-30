package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.fatesg.api.UnidadeEnsino;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.UnidadeEnsinoDao;

@Controller("UnidadeEnsinoControl")
@Scope("conversation")
public class UnidadeEnsinoControl {

	private UnidadeEnsino unidadeEnsino = new UnidadeEnsino();

	@Autowired
	private UnidadeEnsinoDao unidadeEnsinoDao;

	private Instituicao instituicao = new Instituicao();

	@Autowired
	private InstituicaoDao instituicaoDao;

	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();

	private List<UnidadeEnsino> unidadesEnsino = new ArrayList<UnidadeEnsino>();

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
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(ActionEvent evt) {
		try {
			unidadeEnsinoDao.excluirPorId(unidadeEnsino.getId());
			unidadeEnsino = new UnidadeEnsino();
			unidadesEnsino = unidadeEnsinoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			unidadesEnsino = unidadeEnsinoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public List<Instituicao> listarInstituicoes(String nome) {
		instituicoes = instituicaoDao.listarPorNome(nome);
        
        return instituicoes;
    }

	public List<Instituicao> completeInstituicoes(String nome) {

		List<Instituicao> resultados = new ArrayList<Instituicao>();
		instituicoes = instituicaoDao.listarPorNome(nome);

		for (Instituicao c : instituicoes) {
			resultados.add(c);
		}

		return resultados;

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

}
