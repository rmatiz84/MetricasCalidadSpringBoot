package co.edu.uniandes.planafiliacion.service.implementation;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.commons.jpa.entity.afiliaciones.PlanAdquiridoEntity;
import co.edu.uniandes.commons.jpa.entity.afiliaciones.PlanSuscripcionEntity;
import co.edu.uniandes.commons.jpa.entity.usuarios.DeportistaEntity;
import co.edu.uniandes.commons.jpa.entity.usuarios.UsuarioEntity;
import co.edu.uniandes.commons.util.EstadosPlanAdquiridoEnum;
import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoIn;
import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoOut;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.jpa.repository.DeportistaRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.PlanAdquiridoRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.PlanSuscripcionRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.UsuarioRepository;
import co.edu.uniandes.planafiliacion.service.IPlanAdquiridoService;
import co.edu.uniandes.planafiliacion.utilities.FechaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa las funcionalidades de la interfaz
 * IPlanAdquiridoService
 *
 * @since 1.0.0
 */
@Slf4j
@Service
public class PlanAdquiridoService implements IPlanAdquiridoService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PlanAdquiridoRepository planAdquiridoRepository;
	@Autowired
	private PlanSuscripcionRepository planSuscripcionRepository;
	@Autowired 
	private DeportistaRepository deportistaRepository;
	@Autowired 
	private UsuarioRepository usuarioRepository;

	@Override
	public PlanAdquiridoOut registrarPlanUsuario(PlanAdquiridoIn plan) throws ElementoNoEncontradoException {
		//Buscar el usuario
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(plan.getIdDeportista());
		if(!usuario.isPresent()) {
			throw new ElementoNoEncontradoException("No se encuentra un usuario registrado");
		}
		//Validar existe el deportista
		DeportistaEntity deportistaOpt = deportistaRepository.findByUsuario(usuario.get());
		if (deportistaOpt == null) {
			log.info("No se encontró el deportista registrar plan{}", plan.getIdDeportista());
			throw new ElementoNoEncontradoException("No se encuentra un deportista registrado");
		}
		//Validar existe el plan de suscripcion
		Optional<PlanSuscripcionEntity> planSuscripcion = planSuscripcionRepository.findById(plan.getIdPlanSuscripcion());
		if (!planSuscripcion.isPresent()) {
			log.info("No se encontró el planSuscripcion registrar plan{}", plan.getIdPlanSuscripcion());
			throw new ElementoNoEncontradoException("No se encuentra un planSuscripcion registrado");
		}
		//Cancelar plan anterior vigente
		PlanAdquiridoEntity planAdquiridoVigente = planAdquiridoRepository.findByDeportistaAndEstado(deportistaOpt, EstadosPlanAdquiridoEnum.VIGENTE);
		if(planAdquiridoVigente!=null) {
			planAdquiridoVigente.setEstado(EstadosPlanAdquiridoEnum.CANCELADO);
			planAdquiridoVigente.setFechaFinVigencia(new Date());
			planAdquiridoRepository.save(planAdquiridoVigente);
		}
		//Guardar nuevo plan adquirido
		PlanAdquiridoEntity planAdquirido = new PlanAdquiridoEntity();
		planAdquirido.setId(UUID.randomUUID().toString());
		planAdquirido.setDeportista(deportistaOpt);
		planAdquirido.setPlanSuscripcion(planSuscripcion.get());
		planAdquirido.setEstado(EstadosPlanAdquiridoEnum.VIGENTE);
		planAdquirido.setFechaInicioVigencia(new Date());
		planAdquirido.setFechaRegistro(new Date());
		planAdquiridoRepository.save(planAdquirido);

		PlanAdquiridoOut respuesta = modelMapper.map(planAdquirido, PlanAdquiridoOut.class);
		respuesta.setPlanSuscripcion(planSuscripcion.get().getNombre());
		respuesta.setFechaInicioVigencia(FechaUtil.obtenerFechaEnTexto(planAdquirido.getFechaInicioVigencia()));
		return respuesta;
	}

}
