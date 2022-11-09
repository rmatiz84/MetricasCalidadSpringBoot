package co.edu.uniandes.planafiliacion.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO Ejercicio donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class PlanEntrenamientoIn {
	private String id;
	
	@NotEmpty(message = "El nombre no puede ser vacio")
	@Size(max = 100, message = "El nombre debe ser maximo de 100 caracteres")
	private String nombre;
	
	@NotEmpty(message = "El objetivo no puede ser vacio")
	@Size(max = 250, message = "El objetivo debe ser maximo de 250 caracteres")
	private String objetivo;
	
	@NotNull(message = "El tiempoTotal no puede ser vacio")
	private Long tiempoTotal;
	
	private String idDeportista;
	
	private Long idDeporte;
	
	@NotEmpty(message = "Los ejercicios no pueden ser vacios")
	private List<String> ejercicios;

}
