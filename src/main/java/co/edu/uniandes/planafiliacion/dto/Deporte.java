package co.edu.uniandes.planafiliacion.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO Usuario donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class Deporte {
  private Long id;
  private String nombre;
  
}
