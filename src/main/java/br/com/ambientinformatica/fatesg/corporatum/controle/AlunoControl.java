package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.ambientjsf.util.UtilFacesRelatorio;
import br.com.ambientinformatica.corporativo.entidade.EnumUf;
import br.com.ambientinformatica.corporativo.entidade.Municipio;
import br.com.ambientinformatica.fatesg.api.entidade.Aluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumStatusAluno;
import br.com.ambientinformatica.fatesg.api.entidade.EnumTipoSexo;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AlunoDao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.MunicipioDao;
import br.com.ambientinformatica.util.UtilCpf;

@Controller("AlunoControl")
@Scope("conversation")
public class AlunoControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno;

	@Autowired
	private AlunoDao alunoDao;
	
	@Autowired
	private MunicipioDao municipioDao;

	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	private EnumStatusAluno status;
	
	private boolean listarTodos = false;
	
	private String nomeLista;
	
	private String cpfCnpjLista;

	private EnumUf uf = EnumUf.GO;
	
	private List<SelectItem> municipios = new ArrayList<SelectItem>();
	
	private Municipio municipio = new Municipio();
	
	public List<SelectItem> getUfs(){
		return UtilFaces.getListEnum(EnumUf.values());
	}

	@PostConstruct
	public void init() {
		atualizarMunicipios();
		listar();
	}

	public void confirmar() {
		try {
			aluno.setStatus(status);
			aluno.setMunicipio(municipio);
			aluno.setUf(uf);
			alunoDao.validarCampos(aluno);
			String cpf = aluno.getCpfCnpj();
			if (UtilCpf.validarCpf(cpf)) {
				alunoDao.alterar(aluno);
				aluno = new Aluno();
			} else {
				UtilFaces.addMensagemFaces("CPF Inválido");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
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
			alunos = alunoDao.listar(listarTodos, status);
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	
	public void imprimirLista(ActionEvent evt){
		try {
			if(alunos != null){
				Map<String, Object> parametros = new HashMap<String, Object>();
				UtilFacesRelatorio.gerarRelatorioFaces("jasper/alunoLista.jasper", alunos, parametros);
			}else{
				UtilFaces.addMensagemFaces("É necessário listar ao menos um Aluno");
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces("Metodo não implementado!");
		}
	}
	
	public void atualizarMunicipios(){
		try {
			municipios.clear();
			List<Municipio> municipiosList = municipioDao.listarPorUf(uf);
			for(Municipio m : municipiosList){
				municipios.add(new SelectItem(m, m.getDescricao()));
			}
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void limpar() {
		aluno = new Aluno();
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

	public String getCpfCnpjLista() {
		return cpfCnpjLista;
	}

	public void setCpfCnpjLista(String cpfCnpjLista) {
		this.cpfCnpjLista = cpfCnpjLista;
	}

	public EnumUf getUf() {
		return uf;
	}

	public void setUf(EnumUf uf) {
		this.uf = uf;
	}
	
	public List<SelectItem> getMunicipios() {
		return municipios;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
}
