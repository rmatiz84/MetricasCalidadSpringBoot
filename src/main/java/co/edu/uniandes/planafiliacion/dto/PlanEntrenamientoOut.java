package co.edu.uniandes.planafiliacion.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO PlanEntrenamientoOut donde se definen atributos a utilizar en la aplicación.
 *
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class PlanEntrenamientoOut {
	private String id;
	private String nombre;
	private String objetivo;
	private Long tiempoTotal;	
	private Deportista deportista;	
	private Deporte deporte;
	private List<Ejercicio> ejercicios;
	private String user;
	private String password;

}
