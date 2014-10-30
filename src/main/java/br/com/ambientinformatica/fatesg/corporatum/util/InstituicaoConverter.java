package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("instituicaoConverter")
public class InstituicaoConverter implements Converter {

	private InstituicaoDao instituicaoDao = (InstituicaoDao) FabricaAbstrata
			.criarObjeto("instituicaoDao");

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String value) {
		if (value != null && !value.trim().equals("")) {
			Instituicao instituicao = new Instituicao();
			try {
				int id = Integer.parseInt(value);

				try {
					instituicao = instituicaoDao.consultar(id);
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {

				return null;
			}
			return instituicao;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Instituicao) value).getId());
		}
	}
}
