package co.edu.uniandes.planafiliacion.dto;

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
public class ValidacionError {

	private String campo;
	private String mensaje;
}
