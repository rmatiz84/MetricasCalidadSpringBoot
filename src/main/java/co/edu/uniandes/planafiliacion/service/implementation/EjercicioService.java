package co.edu.uniandes.planafiliacion.service.implementation;

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
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.jpa.entity.EjercicioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.SocioNegocioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.UsuarioEntity;
import co.edu.uniandes.planafiliacion.jpa.repository.EjercicioRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.SocioNegocioRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.UsuarioRepository;
import co.edu.uniandes.planafiliacion.service.IEjercicioService;
import co.edu.uniandes.planafiliacion.utilities.EstadosEjercicioEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa las funcionalidades de la interfaz
 * EjercicioPersistenceOutPort
 *
 * @since 1.0.0
 */
@Slf4j
@Service
public class EjercicioService implements IEjercicioService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EjercicioRepository ejercicioRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private SocioNegocioRepository socioNegocioRepository;

	@Override
	public Ejercicio getEjercicioById(String id) throws ElementoNoEncontradoException {
		Optional<EjercicioEntity> optEjercicio = ejercicioRepository.findById(id);
		if (optEjercicio.isPresent()) {
			return modelMapper.map(optEjercicio.get(), Ejercicio.class);
		} else {
			log.info("No se encontró el Ejercicio {}", id);
			throw new ElementoNoEncontradoException("No se encontró el Ejercicio " + id);
		}
	}

	@Override
	public List<Ejercicio> getEjercicios() throws ElementoNoEncontradoException {
		List<EjercicioEntity> ejercicioEntityList = ejercicioRepository.findAll();
		if (Objects.isNull(ejercicioEntityList) || ejercicioEntityList.isEmpty()) {
			log.info("No se encontraron Ejercicios");
			throw new ElementoNoEncontradoException("No se encontraron Ejercicios");
		} else {
			return modelMapper.map(ejercicioEntityList, new TypeToken<List<Ejercicio>>() {
			}.getType());
		}
	}

	@Override
	public Ejercicio registrarEjercicio(Ejercicio ejercicio, String usuario) throws ElementoNoEncontradoException{
		// Validar que existe el usuario y socio de negocio
		// Buscar el usuario
		Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(usuario);
		if (!usuarioOpt.isPresent()) {
			throw new ElementoNoEncontradoException("No se encuentra un usuario registrado");
		}

		SocioNegocioEntity socio = socioNegocioRepository.findByUsuario(usuarioOpt.get());
		if (socio == null) {
			throw new ElementoNoEncontradoException("No se encuentra un socio de negocio registrado para el usuario");
		}

		EjercicioEntity entidad = modelMapper.map(ejercicio, EjercicioEntity.class);
		if (entidad.getId() == null) {
			entidad.setId(UUID.randomUUID().toString());
		}
		
		entidad.setEntrenador(socio);
		entidad.setFechaRegistro(new Date());
		entidad.setEstado(EstadosEjercicioEnum.ACTIVO);
		ejercicioRepository.save(entidad);

		Ejercicio respuesta = modelMapper.map(entidad, Ejercicio.class);
		log.info("Se almacena el Evento {}", respuesta.getId());
		return respuesta;

	}
	
	@Override
	public void inactivarEjercicio(String id) throws ElementoNoEncontradoException {
		Optional<EjercicioEntity> optEjercicio = ejercicioRepository.findById(id);
		if (optEjercicio.isPresent()) {
			EjercicioEntity entidad = optEjercicio.get();
			entidad.setEstado(EstadosEjercicioEnum.INACTIVO);
			ejercicioRepository.save(entidad);
		} else {
			log.info("No se encontró el Ejercicio {}", id);
			throw new ElementoNoEncontradoException("No se encontró el Ejercicio " + id);
		}
	}

}
