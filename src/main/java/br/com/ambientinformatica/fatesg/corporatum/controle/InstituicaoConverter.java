package br.com.ambientinformatica.fatesg.corporatum.controle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.Instituicao;
import br.com.ambientinformatica.fatesg.corporatum.persistencia.InstituicaoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("instituicaoConverter")
public class InstituicaoConverter implements Converter {
	
	
	   private InstituicaoDao instituicaoDao = (InstituicaoDao)FabricaAbstrata.criarObjeto("instituicaoDao");
	   
	   @Override
	   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
	       if (value == null || value.equals("")) {  
	           return "";  
	       } else {  
	           return String.valueOf(((Instituicao)value).getId());  
	       }  
	   }

	   @Override
	   public Object getAsObject(FacesContext context, UIComponent component, String value) {
	      if (value != null && !value.trim().equals("")) {  
	         Instituicao instituicao = new Instituicao();
	         try {  
	         	long id = Long.parseLong(value);  

	            try {
	            	instituicao = instituicaoDao.consultar(id);
	            } catch (PersistenciaException e) {
	               e.printStackTrace();
	            }
	         } catch(NumberFormatException exception) {  
	            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Cliente escolhido não é válido"));
	            //return null;
	         }  
	         return instituicao;  
	      }else{
	         return null;
	      }
	   }
	}  




