package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoColaborador;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.api.entidade.EnumUf;
import br.com.ambientinformatica.fatesg.api.entidade.Municipio;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.MunicipioDao;
import br.com.ambientinformatica.util.UtilCpf;

@Controller("ColaboradorControl")
@Scope("conversation")
public class ColaboradorControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Colaborador colaborador = new Colaborador();

	@Autowired
	private ColaboradorDao colaboradorDao;
	
	@Autowired
	private MunicipioDao municipioDao;
	
	private EnumUf uf;

	private List<Colaborador> colaboradores = new ArrayList<Colaborador>();
	
	private String filtroGlobal = "";
	
	private SelectItem tipo = new SelectItem();
	
	private List<Municipio> municipios = new ArrayList<>();

	@PostConstruct
	public void init() {
		if(colaborador != null && colaborador.getMunicipio() != null){
			uf = colaborador.getMunicipio().getUf();
		}else{
			uf = EnumUf.GO;
		}
		atualizarMunicipios();
		listar();
	}

	public void confirmar() {
		try {
			colaboradorDao.verificarCampos(colaborador);
			String cpf = colaborador.getCpfCnpj();
			if (UtilCpf.validarCpf(cpf)) {
				colaboradorDao.alterar(colaborador);
				listar();
				colaborador = new Colaborador();
				UtilFaces.addMensagemFaces("Operação realizada com sucesso");
			} else {
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
			UtilFaces.addMensagemFaces("Operação realizada com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public void novoColaborador(){
		colaborador = new Colaborador();
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.execute("PF('dlg1').show();");	
	}

	public void listar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("colaborador", new Colaborador());
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void filtrarPorNome() {
		colaboradores = colaboradorDao.listarPorNome(filtroGlobal);
		if (colaboradores.isEmpty()) {
			colaboradores = colaboradorDao.listarPorNome(filtroGlobal);
		}
	}
	public void filtrarPorTipo() {
		colaboradores = colaboradorDao.listarPorTipo(tipo);
		if (colaboradores.isEmpty()) {
			colaboradores = colaboradorDao.listarPorTipo(tipo);
		}
	}

	public void limpar() {
		colaborador = new Colaborador();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("colaborador.jsf");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public void limparConsulta() {
		filtroGlobal = "";
		try {
			colaboradores = colaboradorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public void atualizarMunicipios(){
		municipios =  municipioDao.listarPorUf(uf, null);
	}

	public List<SelectItem> getUfs(){
		return UtilFaces.getListEnum(EnumUf.values());
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

	public List<SelectItem> getTiposSexo() {
		return UtilFaces.getListEnum(EnumTipoSexo.values());
	}

	public List<SelectItem> getTiposColaboradores() {
		return UtilFaces.getListEnum(EnumTipoColaborador.values());
	}

	public String getFiltroGlobal() {
		return filtroGlobal;
	}

	public void setFiltroGlobal(String filtroGlobal) {
		this.filtroGlobal = filtroGlobal;
	}

	public EnumUf getUf() {
		return uf;
	}

	public void setUf(EnumUf uf) {
		this.uf = uf;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	
}
