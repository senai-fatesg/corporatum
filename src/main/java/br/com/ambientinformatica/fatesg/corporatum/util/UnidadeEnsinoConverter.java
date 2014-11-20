package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.api.UnidadeEnsino;
import br.com.ambientinformatica.fatesg.corporatum.dao.UnidadeEnsinoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("unidadeEnsinoConverter")
public class UnidadeEnsinoConverter implements Converter {
	
	
	   private UnidadeEnsinoDao unidadeEnsinoDao = (UnidadeEnsinoDao)FabricaAbstrata.criarObjeto("unidadeEnsinoDao");
	   
	   @Override
	   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
	       if (value == null || value.equals("")) {  
	           return "";  
	       } else {  
	           return String.valueOf(((UnidadeEnsino)value).getId());  
	       }  
	   }


	   @Override
	   public Object getAsObject(FacesContext context, UIComponent component, String value) {
	      if (value != null && !value.trim().equals("")) {  
			UnidadeEnsino unidadeEnsino = new UnidadeEnsino();
	         try {  
	         	long id = Long.parseLong(value);  

	            try {
	            	unidadeEnsino = unidadeEnsinoDao.consultar(id);
	            } catch (PersistenciaException e) {
	               e.printStackTrace();
	            }
	         } catch(NumberFormatException exception) {  
	            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Inserção dos dados!", "UnidadeEnsino escolhida não é válida"));
	            //return null;
	         }  
	         return unidadeEnsino;  
	      }else{
	         return null;
	      }
	   }
	}  




