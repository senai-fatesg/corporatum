package br.com.ambientinformatica.fatesg.corporatum.util;

import java.util.List;

import br.com.ambientinformatica.jpa.exception.PersistenciaException;

public class CorporatumException extends PersistenciaException{

   private static final long serialVersionUID = 1L;

   public CorporatumException() {
      super();
   }

   public CorporatumException(List<String> listaMensagens) {
      super(listaMensagens);
   }

   public CorporatumException(String message, Throwable cause) {
      super(message, cause);
   }

   public CorporatumException(String message) {
      super(message);
   }

   public CorporatumException(Throwable cause) {
      super(cause);
   }
}