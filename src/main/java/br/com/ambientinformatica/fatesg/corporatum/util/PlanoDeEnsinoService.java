package br.com.ambientinformatica.fatesg.corporatum.util;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.fatesg.api.PlanoDeEnsino;

@Service("planoEnsinoService")
public class PlanoDeEnsinoService{

	public void openDialogCompetencia() {
        RequestContext.getCurrentInstance().openDialog("competencia");
    }
	
	public void openDialogHabilidade() {
        RequestContext.getCurrentInstance().openDialog("habilidade");
    }
	
	public void openDialogBaseTecnologica() {
        RequestContext.getCurrentInstance().openDialog("basetecnologica");
    }	
	
	public void openDialogMetodologiaEnsino() {
        RequestContext.getCurrentInstance().openDialog("metodologiaensino");
    }
	
	public void openDialogRecursoDidatico() {
        RequestContext.getCurrentInstance().openDialog("recursodidatico");
    }
	
	public void openDialogBibliografia() {
        RequestContext.getCurrentInstance().openDialog("bibliografia");
    }
}
