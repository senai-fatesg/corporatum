package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.ambientinformatica.fatesg.api.Disciplina;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.DisciplinaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@ManagedBean
public class DisciplinaConverter implements Converter {
	
	private DisciplinaDao disciplinaDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		
		try{
			Disciplina disciplina = disciplinaDao.consultar(id);
					return disciplina;
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
	public String getAsString(FacesContext arg0, UIComponent arg1, Object disciplina) {
		try{
			Disciplina d = (Disciplina) disciplina; 
			return d.getId().toString();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
