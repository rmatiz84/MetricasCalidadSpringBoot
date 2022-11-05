package co.edu.uniandes.planafiliacion.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO PlanAdquiridoIn donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class PlanAdquiridoIn {

  @NotEmpty(message = "El id deportista no puede ser vacio")
  private String idDeportista;
  
  @NotNull(message = "El id plan de suscripcion no puede ser vacio")
  private Long idPlanSuscripcion;
  
  
  
}
