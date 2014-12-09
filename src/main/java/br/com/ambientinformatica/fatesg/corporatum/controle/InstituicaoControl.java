package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.dao.InstituicaoDao;
import br.com.ambientinformatica.util.UtilCnpj;

@Controller("InstituicaoControl")
@Scope("conversation")
public class InstituicaoControl {

	private Instituicao instituicao = new Instituicao();

	@Autowired
	private InstituicaoDao instituicaoDao;

	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();

	private String filtroGlobal = "";

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {

			String cnpj = instituicao.getCnpj();
			instituicaoDao.verificarCampos(instituicao);
			if (UtilCnpj.validarCnpj(cnpj)) {
				instituicaoDao.alterar(instituicao);
				instituicoes = instituicaoDao.listar();
				instituicao = new Instituicao();
				UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
			} else {
				UtilFaces.addMensagemFaces("CNPJ Inválido");
			}

		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir() {
		try {
			instituicaoDao.excluirPorId(instituicao.getId());
			instituicao = new Instituicao();
			instituicoes = instituicaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			instituicoes = instituicaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void filtrarPorNome() {
		try {
			instituicoes = instituicaoDao.consultarPeloNome(filtroGlobal);
			if (instituicoes.isEmpty()) {
				instituicoes = instituicaoDao.consultarPeloNome(filtroGlobal);
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limparConsulta() {
		filtroGlobal = "";
		try {
			instituicoes = instituicaoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public void limpar(){
		instituicao = new Instituicao();
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

	public String getFiltroGlobal() {
		return filtroGlobal;
	}

	public void setFiltroGlobal(String filtroGlobal) {
		this.filtroGlobal = filtroGlobal;
	}

}
