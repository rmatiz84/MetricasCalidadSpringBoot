package co.edu.uniandes.planafiliacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoIn;
import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoOut;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.service.IPlanAdquiridoService;
import co.edu.uniandes.planafiliacion.utilities.Constantes;

/**
 * Clase donde se implementan servicios REST para la gestión de departamentos
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping(Constantes.PATH_PLAN_AFILIACION)
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PlanAfiliacionController {

	@Autowired
	private IPlanAdquiridoService iPlanAdquiridoService;

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/health/ping", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> healthPing() throws ElementoNoEncontradoException {
		return new ResponseEntity<>("pong", HttpStatus.OK);
	}

	/**
	 * Servicio para almacenar un plan de afiliacion a usuario
	 * 
	 * @param perfil
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlanAdquiridoOut> registrarPlanUsuario(@Validated @RequestBody PlanAdquiridoIn plan)
			throws ElementoNoEncontradoException {
		return new ResponseEntity<>(iPlanAdquiridoService.registrarPlanUsuario(plan), HttpStatus.CREATED);
	}

}
