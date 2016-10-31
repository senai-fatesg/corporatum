package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;

@Controller("UsuarioLogadoControl")
@Scope("session")
public class UsuarioLogadoControl implements Serializable{

	private static final long serialVersionUID = 1L;

	private Colaborador colaborador;

	@Autowired
	private ColaboradorDao colaboradorDao;

	@PostConstruct
	public void init() {
		try {
			consultarUsuarioLogado();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	private void consultarUsuarioLogado(){
		try {
			HttpServletRequest req = UtilFaces.getRequest();
			colaborador = colaboradorDao.consultarPorCpf(req.getUserPrincipal().getName());
		} catch(Exception e){
			UtilFaces.addMensagemFaces(e);
		}
	}

	public static Colaborador getUsuarioConfigurado() {
		return (Colaborador) UtilFaces.getObjetoManagedBean("#{UsuarioLogadoControl.colaborador}");
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

}
