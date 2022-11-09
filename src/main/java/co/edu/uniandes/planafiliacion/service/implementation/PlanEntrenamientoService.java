package co.edu.uniandes.planafiliacion.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.planafiliacion.dto.Ejercicio;
import co.edu.uniandes.planafiliacion.dto.PlanEntrenamientoIn;
import co.edu.uniandes.planafiliacion.dto.PlanEntrenamientoOut;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.jpa.entity.DeporteEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.DeportistaEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.EjercicioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.PlanEntrenamientoEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.SocioNegocioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.UsuarioEntity;
import co.edu.uniandes.planafiliacion.jpa.repository.DeporteRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.DeportistaRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.EjercicioRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.PlanEntrenamientoRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.SocioNegocioRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.UsuarioRepository;
import co.edu.uniandes.planafiliacion.service.IPlanEntrenamientoService;
import co.edu.uniandes.planafiliacion.utilities.Constantes;
import co.edu.uniandes.planafiliacion.utilities.EstadosEjercicioEnum;
import co.edu.uniandes.planafiliacion.utilities.EstadosPlanEntrenamientoEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa las funcionalidades de la interfaz
 * IPlanEntrenamientoService
 *
 * @since 1.0.0
 */
@Slf4j
@Service
public class PlanEntrenamientoService implements IPlanEntrenamientoService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PlanEntrenamientoRepository planEntrenamientoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private SocioNegocioRepository socioNegocioRepository;
	@Autowired
	private DeporteRepository deporteRepository;
	@Autowired
	private DeportistaRepository deportistaRepository;
	@Autowired
	private EjercicioRepository ejercicioRepository;

	@Override
	public PlanEntrenamientoOut getPlanEntrenamientoById(String id) throws ElementoNoEncontradoException {
		Optional<PlanEntrenamientoEntity> optPlanEntrenamiento = planEntrenamientoRepository.findById(id);
		if (optPlanEntrenamiento.isPresent()) {
			return modelMapper.map(optPlanEntrenamiento.get(), PlanEntrenamientoOut.class);
		} else {
			log.info("No se encontró el Ejercicio {}", id);
			throw new ElementoNoEncontradoException("No se encontró el Ejercicio " + id);
		}
	}

	@Override
	public PlanEntrenamientoOut getPlanEntrenamientoDeportista(String idDeportista)
			throws ElementoNoEncontradoException {
		List<PlanEntrenamientoEntity> planEntrenamientoEntityList = planEntrenamientoRepository
				.findByDeportistaAndEstado(idDeportista, EstadosPlanEntrenamientoEnum.ACTIVO);
		if (Objects.isNull(planEntrenamientoEntityList) || planEntrenamientoEntityList.isEmpty()) {
			log.info("No se encontró el PlanEntrenamiento activo para el deportista");
			throw new ElementoNoEncontradoException("No se encontró el PlanEntrenamiento activo para el deportista");
		} else {
			return modelMapper.map(planEntrenamientoEntityList.get(0), PlanEntrenamientoOut.class);
		}
	}

	@Override
	public List<PlanEntrenamientoOut> getPlanesEntrenamientoPorEntrenador(String idEntrenador)
			throws ElementoNoEncontradoException {
		List<PlanEntrenamientoEntity> planEntrenamientoEntityList = planEntrenamientoRepository
				.findByEntrenador(idEntrenador);
		if (Objects.isNull(planEntrenamientoEntityList) || planEntrenamientoEntityList.isEmpty()) {
			System.out.println("No se encontraron PlanEntrenamientos para el entrenador");
			throw new ElementoNoEncontradoException("No se encontraron PlanEntrenamientos para el entrenador");
		} else {
			return modelMapper.map(planEntrenamientoEntityList, new TypeToken<List<PlanEntrenamientoOut>>() {
			}.getType());
		}
	}

	@Override
	public PlanEntrenamientoOut registrarPlanEntrenamiento(PlanEntrenamientoIn planEntrenamiento, String usuario)
			throws ElementoNoEncontradoException {
		// Validar que existe el usuario y socio de negocio
		// Buscar el usuario
		Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(usuario);
		if (!usuarioOpt.isPresent()) {
			System.out.println("No se encuentra un usuario registrado");
			throw new ElementoNoEncontradoException("No se encuentra un usuario registrado");
		}

		SocioNegocioEntity socio = socioNegocioRepository.findByUsuario(usuarioOpt.get());
		if (socio == null) {
			System.out.println("No se encuentra un socio de negocio registrado para el usuario");
			throw new ElementoNoEncontradoException("No se encuentra un socio de negocio registrado para el usuario");
		}

		// Validar deporte
		Optional<DeporteEntity> deporte = deporteRepository.findById(planEntrenamiento.getIdDeporte());
		if (!deporte.isPresent()) {
			System.out.println("No se encuentra un deporte registrado");
			throw new ElementoNoEncontradoException("No se encuentra un deporte registrado");
		}

		// Validar deportista
		Optional<DeportistaEntity> deportista = deportistaRepository.findById(planEntrenamiento.getIdDeportista());
		if (!deportista.isPresent()) {
			System.out.println("No se encuentra un deporte registrado");
			throw new ElementoNoEncontradoException("No se encuentra un deportista registrado");
		}

		PlanEntrenamientoEntity entidad = null;
		if (planEntrenamiento.getId() == null) {
			entidad = new PlanEntrenamientoEntity();
			entidad.setId(UUID.randomUUID().toString());
		} else {
			Optional<PlanEntrenamientoEntity> optEntidad = planEntrenamientoRepository
					.findById(planEntrenamiento.getId());
			if (!optEntidad.isPresent()) {
				System.out.println("No se encuentra un plan de entrenamiento registrado para el id enviado");
				throw new ElementoNoEncontradoException(
						"No se encuentra un plan de entrenamiento registrado para el id enviado");
			}
			entidad = optEntidad.get();
		}
		
		List<EjercicioEntity> ejercicios = new ArrayList<>();
		for(String ejer: planEntrenamiento.getEjercicios()) {
			EjercicioEntity ejercicio = ejercicioRepository.findByIdAndEstado(ejer, EstadosEjercicioEnum.ACTIVO);
			if(ejercicio==null) {
				throw new ElementoNoEncontradoException(
						"No se encuentra un ejercicio activo registrado para el id enviado");
			}
			ejercicios.add(ejercicio);
		}

		entidad.setEjercicios(ejercicios);
		entidad.setDeportista(deportista.get());
		entidad.setDeporte(deporte.get());
		entidad.setEntrenador(socio);
		entidad.setNombre(planEntrenamiento.getNombre());
		entidad.setObjetivo(planEntrenamiento.getObjetivo());
		entidad.setTiempoTotal(planEntrenamiento.getTiempoTotal());
		entidad.setFechaRegistro(new Date());
		entidad.setEstado(EstadosPlanEntrenamientoEnum.ACTIVO);
		planEntrenamientoRepository.save(entidad);

		PlanEntrenamientoOut respuesta = modelMapper.map(entidad, PlanEntrenamientoOut.class);
		System.out.println("Se almacena el Evento");
		return respuesta;

	}

}
