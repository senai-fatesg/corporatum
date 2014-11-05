package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter(forClass = Disciplina.class)
public class DisciplinaConverter implements Converter {

	private DisciplinaDao disciplinaDao = (DisciplinaDao) FabricaAbstrata.criarObjeto("disciplinaDao");

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Disciplina) value).getId());
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Disciplina disciplina = new Disciplina();
			try {
				disciplina = disciplinaDao.consultar(value);

			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Cliente escolhido não é válido"));
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			return disciplina;
		} else {
			return null;
		}
	}
}
