package co.edu.uniandes.planafiliacion.dto;

import javax.validation.constraints.NotEmpty;
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
public class Ejercicio {
	private String id;
	
	@NotEmpty(message = "El nombre no puede ser vacio")
	private String nombre;
	
	private Integer series;
	
	private Integer repeticiones;
	
	private Integer tiempoDescanso;
	
	@Size(min = 1, max = 400, message = "Las instrucciones no pueden ser vacias y debe ser máximo de 400 caracteres")
	private String instrucciones;
	
	@Size(max = 300, message = "El nombre no puede ser vacio")
	private String imagen;
	
	private String estado;

}
