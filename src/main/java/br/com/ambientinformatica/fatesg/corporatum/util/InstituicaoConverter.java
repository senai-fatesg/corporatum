package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@ManagedBean
public class InstituicaoConverter implements Converter {
	
	private InstituicaoDao instituicaoDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		
		try{
			Instituicao instituicao = instituicaoDao.consultar(id);
					return instituicao;
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object instituicao) {
		try{
			Instituicao i = (Instituicao) instituicao; 
			return i.getId().toString();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
