package co.edu.uniandes.planafiliacion.service;

import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoIn;
import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoOut;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;

/**
 * Interfaz con todos los métodos disponibles para gestionar Departamentos
 *
 * @since 1.0.0
 */
public interface IPlanAdquiridoService {

  
	/**
	 * Metodo para guardar un PlanAdquirido
	 * @param usuario
	 * @return
	 */
	public PlanAdquiridoOut registrarPlanUsuario(PlanAdquiridoIn plan) throws ElementoNoEncontradoException;

}
