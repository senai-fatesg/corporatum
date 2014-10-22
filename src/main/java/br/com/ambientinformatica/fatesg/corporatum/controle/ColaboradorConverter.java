package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@FacesConverter("colaboradorConverter")
public class ColaboradorConverter implements Converter {

	private ColaboradorDao colaboradorDao;

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Colaborador) value).getId());
		}
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Colaborador colaborador = new Colaborador();
			try {
				colaborador = colaboradorDao.consultar(Long.parseLong(value));
			} catch (PersistenciaException e) {
				e.printStackTrace();
			} catch (NumberFormatException exception) {
				return null;
			}
			return colaborador;
		} else {
			return null;
		}
	}
}
