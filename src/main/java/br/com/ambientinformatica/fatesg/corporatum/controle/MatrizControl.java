package br.com.ambientinformatica.fatesg.corporatum.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.Avaliacao;
import br.com.ambientinformatica.fatesg.api.Matriz;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.MatrizDao;

@Controller("MatrizControl")
@Scope("conversation")
public class MatrizControl {

	private Matriz matriz = new Matriz();
	@Autowired
	private MatrizDao matrizDao;
	
	private List<Matriz> matrizes = new ArrayList<Matriz>();
	

   @PostConstruct
   public void init(){
      listar(null);
   }
   
	public void confirmar(ActionEvent evt){
		try {
			matrizDao.alterar(matriz);
         listar(evt);
         matriz = new Matriz();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public void listar(ActionEvent evt){
		try {
			matrizes = matrizDao.listar();
		} catch (Exception e) {
		   UtilFaces.addMensagemFaces(e);
		}
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public List<Matriz> getMatrizes() {
		return matrizes;
	}	
}