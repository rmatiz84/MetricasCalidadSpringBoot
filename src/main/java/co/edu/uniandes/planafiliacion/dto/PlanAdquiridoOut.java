package co.edu.uniandes.planafiliacion.dto;

import co.edu.uniandes.planafiliacion.utilities.EstadosPlanAdquiridoEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO PlanAdquiridoOut donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class PlanAdquiridoOut {
  private String id;
  private String idDeportista;
  private String planSuscripcion;
  private EstadosPlanAdquiridoEnum estado;
  private String fechaInicioVigencia;
  private String fechaFinVigencia;
}
