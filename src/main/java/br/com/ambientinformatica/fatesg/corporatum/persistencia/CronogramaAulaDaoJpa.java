package br.com.ambientinformatica.fatesg.corporatum.persistencia;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.fatesg.api.CronogramaAula;

@Repository("cronogramaAulaDao")
public class CronogramaAulaDaoJpa extends PersistenciaJpa<CronogramaAula> implements CronogramaAulaDao{

   private static final long serialVersionUID = 1L;

}
