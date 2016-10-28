package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ambientjsf.util.UtilFacesRelatorio;
import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumStatusAluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.api.entidade.EnumUf;
import br.com.ambientinformatica.fatesg.api.entidade.Municipio;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.MunicipioDao;
import br.com.ambientinformatica.util.UtilCpf;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno = new Aluno();

	private List<Aluno> alunos = new ArrayList<Aluno>();

	private EnumStatusAluno status;

	private boolean listarTodos = false;

	private String nomeLista;

	private EnumUf uf;

	private List<Municipio> municipios = new ArrayList<>();

	@Autowired
	private AlunoDao alunoDao;

	@Autowired
	private MunicipioDao municipioDao;


	@PostConstruct
	public void init() {
		if(aluno != null && aluno.getMunicipio() != null){
			uf = aluno.getMunicipio().getUf();
		}else{
			uf = EnumUf.GO;
		}
		atualizarMunicipios();
		listar();
	}

	public void confirmar(){
		try {
			alunoDao.validarCampos(aluno);
			alunoDao.alterar(aluno);
			limpar();
			UtilFaces.addMensagemFaces("Aluno cadastrado com sucesso.");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void editarAluno(Aluno aluno){
		this.aluno = aluno;
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.execute("PF('dlg1').show();");
	}

	public void excluir(ActionEvent evt) {
		try {
			aluno.setStatus(EnumStatusAluno.INATIVO);
			alunoDao.alterar(aluno);
			listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar() {
		try {
			alunos = alunoDao.listar(nomeLista, listarTodos, status);
			nomeLista = "";
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}


	public void imprimirLista(ActionEvent evt){
		try {
			if(alunos != null){
				Map<String, Object> parametros = new HashMap<String, Object>();
				UtilFacesRelatorio.gerarRelatorioFaces("jasper/relatorioAlunos.jasper", alunos, parametros);
			}else{
				UtilFaces.addMensagemFaces("É necessário listar ao menos um Aluno");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Metodo não implementado!");
		}
	}

	public void atualizarMunicipios(){
		municipios =  municipioDao.listarPorUf(uf, null);
	}

	public List<SelectItem> getUfs(){
		return UtilFaces.getListEnum(EnumUf.values());
	}

	public void limpar() {
		try {
			aluno = new Aluno();
			FacesContext.getCurrentInstance().getExternalContext().redirect("alunoLista.jsf");			
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e.getMessage());
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}	

	public List<SelectItem> getTiposSexo() {
		return UtilFaces.getListEnum(EnumTipoSexo.values());
	}

	public List<SelectItem> getStatusAluno() {
		return UtilFaces.getListEnum(EnumStatusAluno.values());
	}

	public boolean isListarTodos() {
		return listarTodos;
	}

	public void setListarTodos(boolean listarTodos) {
		this.listarTodos = listarTodos;
	}

	public void setStatus(EnumStatusAluno status) {
		this.status = status;
	}

	public EnumStatusAluno getStatus() {
		return status;
	}

	public String getNomeLista() {
		return nomeLista;
	}

	public void setNomeLista(String nomeLista) {
		this.nomeLista = nomeLista;
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
