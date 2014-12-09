package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Curso;
import br.com.ambientinformatica.fatesg.api.persistencia.CursoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {

	private CursoDao cursoDao = (CursoDao) FabricaAbstrata
			.criarObjeto("cursoDao");

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Curso) value).getId());
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Curso curso = new Curso();
			try {
				String id = (value);

				try {
					curso = cursoDao.consultar(id);
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				UtilFaces
						.addMensagemFaces("Curso não é válido. Erro no Converter");
			}
			return curso;
		} else {
			return null;
		}
	}

}
