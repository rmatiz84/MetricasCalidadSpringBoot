package co.edu.uniandes.planafiliacion.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Departamento donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
  private String codigoResultado;
  private String descripcionRespuesta;
  private Object result;
  private List<ValidacionError> validaciones;
}
