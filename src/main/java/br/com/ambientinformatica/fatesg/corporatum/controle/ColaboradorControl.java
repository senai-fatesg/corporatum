package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.fatesg.api.EnumTipoColaborador;
import br.com.ambientinformatica.fatesg.api.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;
import br.com.ambientinformatica.fatesg.corporatum.util.ValidaCPF;

@Controller("ColaboradorControl")
@Scope("conversation")
public class ColaboradorControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Colaborador colaborador = new Colaborador();

	@Autowired
	private ColaboradorDao colaboradorDao;

	private List<Colaborador> colaboradores = new ArrayList<Colaborador>();

	private EnumTipoSexo tipoSexo;

	private EnumTipoColaborador tipoColaborador;

	private ValidaCPF validaCPF = new ValidaCPF();

	Boolean cpfValido = true;

	@PostConstruct
	public void init() {
		listar(null);
	}

	public List<SelectItem> getTiposSexo() {
		return UtilFaces.getListEnum(EnumTipoSexo.values());
	}

	public List<SelectItem> getTiposColaboradores() {
		return UtilFaces.getListEnum(EnumTipoColaborador.values());
	}

	public void confirmar(ActionEvent evt) {
		try {
			colaboradorDao.verificarCampos(colaborador);
			String cpf = colaborador.getCpfCnpj();
			cpfValido = validaCPF.validacpf(cpf);

			if (!cpfValido == false) {

				if (!tipoSexo.equals("")) {
					colaboradorDao.alterar(colaborador);
					listar(evt);
					colaborador = new Colaborador();
				} else {
					UtilFaces.addMensagemFaces("Selecione um sexo");
				}
			}else{
				UtilFaces.addMensagemFaces("CPF Inválido");
			}

		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(ActionEvent evt) {
		try {
			colaboradorDao.excluirPorId(colaborador.getId());
			colaborador = new Colaborador();
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt) {
		try {
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void limpar() {
		colaborador = new Colaborador();
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public EnumTipoSexo getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(EnumTipoSexo tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public EnumTipoColaborador getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(EnumTipoColaborador tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

}
