package br.com.ambientinformatica.fatesg.corporatum.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ambientinformatica.fatesg.api.entidade.Colaborador;

@Entity
public class HistoricoLogin {

   @Id
   @GeneratedValue(generator="historico_login_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(name="historico_login_seq", sequenceName="historico_login_seq", allocationSize=1, initialValue=1)
   private Long id;
   
   @ManyToOne
   private Colaborador colaborador;
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date data;


   public Date getData() {
      return data;
   }

   public void setData(Date data) {
      this.data = data;
   }

   public Long getId() {
      return id;
   }
   
}
