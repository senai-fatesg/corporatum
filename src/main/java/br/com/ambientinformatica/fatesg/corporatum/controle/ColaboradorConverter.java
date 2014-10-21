package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component("colaboradorConverter")
public class ColaboradorConverter implements Converter {

	private ColaboradorDao colaboradorDao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {

		try {
			Colaborador colaborador = colaboradorDao.consultar(Long
					.parseLong(id));
			return colaborador;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component,
			Object colaborador) {
		try {
			if (colaborador != null) {
				Colaborador c = (Colaborador) colaborador;
				return c.getId().toString();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
