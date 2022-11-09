package co.edu.uniandes.planafiliacion.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO Deportista donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class Deportista {
	
  private String id;
  private String nombres;
  private String apellidos;
  private String tipoIdentificacion;
  private String numeroIdentificacion;
  
}
