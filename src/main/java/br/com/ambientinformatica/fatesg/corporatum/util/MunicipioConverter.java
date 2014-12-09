package br.com.ambientinformatica.fatesg.corporatum.util;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.corporativo.entidade.Municipio;
import br.com.ambientinformatica.fatesg.corporatum.dao.MunicipioDao;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("municipioConverter")
public class MunicipioConverter implements Converter{

   private MunicipioDao municipioDao = (MunicipioDao) FabricaAbstrata.criarObjeto("municipioDao");
   
   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value) {
      try {
         Integer id = Integer.parseInt(value);
         return municipioDao.consultar(id);
      } catch (Exception e) {
         return null;
      }
   }

   @Override
   public String getAsString(FacesContext context, UIComponent component, Object value) {
      try {
         return String.valueOf(((Municipio)value).getId());
      } catch (Exception e) {
         return "";
      }
   }

}
