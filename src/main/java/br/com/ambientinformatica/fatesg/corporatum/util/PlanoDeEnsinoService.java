package br.com.ambientinformatica.fatesg.corporatum.util;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Service;

@Service("planoEnsinoService")
public class PlanoDeEnsinoService{

	public void openDialogCompetencia() {
        RequestContext.getCurrentInstance().openDialog("competencia");
    }
}
