package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Colaborador;
import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.dao.ColaboradorDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("colaboradorConverter")
public class ColaboradorConverter implements Converter {

	private ColaboradorDao colaboradorDao = (ColaboradorDao) FabricaAbstrata
			.criarObjeto("colaboradorDao");

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Instituicao) value).getId());
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Colaborador colaborador = new Colaborador();
			try {
				long id = Long.parseLong(value);

				try {
					colaborador = colaboradorDao.consultar(id);
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Erro de Inserção dos dados!",
						"Item selecionado não é válido"));
				// return null;
			}
			return colaborador;
		} else {
			return null;
		}
	}
}
