package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.ambientinformatica.fatesg.api.Curso;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.CursoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

@ManagedBean
public class CursoConverter implements Converter {
	
	private CursoDao cursoDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		
		try{
			Curso curso = cursoDao.consultar(id);
					return curso;
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
	public String getAsString(FacesContext arg0, UIComponent arg1, Object curso) {
		try{
			Curso c = (Curso) curso; 
			return c.getId().toString();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
