package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.ColaboradorDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@FacesConverter(forClass = Colaborador.class)
public class ColaboradorConverter implements Converter {

	private ColaboradorDao colaboradorDao;

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value != null) {
			Colaborador colaborador = (Colaborador) value;
			return colaborador.getId().toString();
		}
		return "";
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Long codigo = Long.valueOf(value);
			try {
				return colaboradorDao.consultar(codigo);
			} catch (PersistenciaException e) {
				e.printStackTrace();
			} catch (NumberFormatException exception) {
				return null;
			}
			return null;
		} else {
			return null;
		}
	}
}
