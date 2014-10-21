package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@Component("disciplinaConverter")
public class DisciplinaConverter implements Converter {

	private DisciplinaDao disciplinaDao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {

		try {
			Disciplina disciplina = disciplinaDao.consultar(id);
			return disciplina;
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
			Object disciplina) {
		try {
			if (disciplina != null) {
				Disciplina d = (Disciplina) disciplina;
				return d.getId();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
