package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@FacesConverter(forClass = Disciplina.class)
public class DisciplinaConverter implements Converter {

	private DisciplinaDao disciplinaDao;

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value != null) {
			Disciplina disciplina = (Disciplina) value;
			return disciplina.getId();
		}
		return "";
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			try {
				return disciplinaDao.consultar(value);
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
