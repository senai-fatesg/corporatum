package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.ambientinformatica.fatesg.api.entidade.Disciplina;
import br.com.ambientinformatica.fatesg.api.persistencia.DisciplinaDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("disciplinaConverter")
public class DisciplinaConverter implements Converter {
	
	
	   private DisciplinaDao disciplinaDao = (DisciplinaDao)FabricaAbstrata.criarObjeto("disciplinaDao");
	   
	   @Override
	   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
	       if (value == null || value.equals("")) {  
	           return "";  
	       } else {  
	           return ((Disciplina)value).getId();  
	       }  
	   }


	   @Override
	   public Object getAsObject(FacesContext context, UIComponent component, String value) {
	      if (value != null && !value.trim().equals("")) {  
			Disciplina disciplina = new Disciplina();
	         try {  
	         	String id = value;  

	            try {
	            	disciplina = disciplinaDao.consultar(id);
	            } catch (PersistenciaException e) {
	               e.printStackTrace();
	            }
	         } catch(NumberFormatException exception) {  
	            UtilFaces.addMensagemFaces("Avaliação escolhida não é válida. Erro no Converter");
	            //return null;
	         }  
	         return disciplina;  
	      }else{
	         return null;
	      }
	   }
	}  




