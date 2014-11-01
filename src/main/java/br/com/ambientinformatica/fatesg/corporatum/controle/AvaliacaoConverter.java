package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Avaliacao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.AvaliacaoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter(forClass=Avaliacao.class)
public class AvaliacaoConverter implements Converter {

	private AvaliacaoDao avaliacaoDao = (AvaliacaoDao) FabricaAbstrata
			.criarObjeto("avaliacaoDao");

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Avaliacao) value).getId());
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Avaliacao avaliacao = new Avaliacao();
			try {
				long id = Long.parseLong(value);

				try {
					avaliacao = avaliacaoDao.consultar(id);
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
			return avaliacao;
		} else {
			return null;
		}
	}
}
