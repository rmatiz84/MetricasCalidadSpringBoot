package co.edu.uniandes.planafiliacion.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

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
import co.edu.uniandes.planafiliacion.utilities.EstadosEjercicioEnum;
import co.edu.uniandes.planafiliacion.utilities.EstadosPlanEntrenamientoEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlanEntrenamientoServiceTest {

	@Spy
	private ModelMapper modelMapper;
	@Mock
	private PlanEntrenamientoRepository planEntrenamientoRepository;
	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private SocioNegocioRepository socioNegocioRepository;
	@Mock
	private DeporteRepository deporteRepository;
	@Mock
	private DeportistaRepository deportistaRepository;
	@Mock
	private EjercicioRepository ejercicioRepository;

	@InjectMocks
	private PlanEntrenamientoService planEntrenamientoService;
	
	
	@Test
	void getPlanEntrenamientoById() throws ElementoNoEncontradoException {
		log.info("Inicio test getPlanEntrenamientoById");
		PlanEntrenamientoEntity evento1 = new PlanEntrenamientoEntity();
		evento1.setId("1");
		Optional<PlanEntrenamientoEntity> opt = Optional.of(evento1);

		List<PlanEntrenamientoEntity> listaUsuario = new ArrayList<>();
		listaUsuario.add(evento1);

		Mockito.when(planEntrenamientoRepository.findById("1")).thenReturn(opt);

		PlanEntrenamientoOut respuesta = planEntrenamientoService.getPlanEntrenamientoById("1");
		Assertions.assertEquals(listaUsuario.get(0).getId(), respuesta.getId());
	}

	@Test
	void getPlanEntrenamientoByIdNotFound() throws ElementoNoEncontradoException {
		log.info("Inicio test getEjercicioByIdNotFound");
		Optional<PlanEntrenamientoEntity> opt = Optional.empty();

		Mockito.when(planEntrenamientoRepository.findById("1")).thenReturn(opt);

		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.getPlanEntrenamientoById("1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encontró el PlanEntrenamiento 1", ex.getMessage());

	}
	
	@Test
	void getPlanEntrenamientoDeportista() throws ElementoNoEncontradoException {
		log.info("Inicio test getPlanEntrenamientoDeportista");
		PlanEntrenamientoEntity evento1 = new PlanEntrenamientoEntity();
		evento1.setId("1");

		List<PlanEntrenamientoEntity> listaUsuario = new ArrayList<>();
		listaUsuario.add(evento1);

		Mockito.when(planEntrenamientoRepository.findByDeportistaAndEstado("1", EstadosPlanEntrenamientoEnum.ACTIVO)).thenReturn(listaUsuario);

		PlanEntrenamientoOut respuesta = planEntrenamientoService.getPlanEntrenamientoDeportista("1");
		Assertions.assertEquals(listaUsuario.get(0).getId(), respuesta.getId());
	}

	@Test
	void getPlanEntrenamientoDeportistaNotFound() throws ElementoNoEncontradoException {
		log.info("Inicio test getPlanEntrenamientoDeportistaNotFound");
		
		Mockito.when(planEntrenamientoRepository.findByDeportistaAndEstado("1", EstadosPlanEntrenamientoEnum.ACTIVO)).thenReturn(null);

		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.getPlanEntrenamientoDeportista("1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encontró el PlanEntrenamiento activo para el deportista", ex.getMessage());

	}
	
	@Test
	void getPlanesEntrenamientoPorEntrenador() throws ElementoNoEncontradoException {
		log.info("Inicio test getPlanesEntrenamientoPorEntrenador");
		PlanEntrenamientoEntity evento1 = new PlanEntrenamientoEntity();
		evento1.setId("1");

		List<PlanEntrenamientoEntity> listaUsuario = new ArrayList<>();
		listaUsuario.add(evento1);

		Mockito.when(planEntrenamientoRepository.findByEntrenador("1")).thenReturn(listaUsuario);

		List<PlanEntrenamientoOut> respuesta = planEntrenamientoService.getPlanesEntrenamientoPorEntrenador("1");
		Assertions.assertEquals(listaUsuario.get(0).getId(), respuesta.get(0).getId());
	}

	@Test
	void getPlanesEntrenamientoPorEntrenadorNotFound() throws ElementoNoEncontradoException {
		log.info("Inicio test getPlanesEntrenamientoPorEntrenadorNotFound");
		
		Mockito.when(planEntrenamientoRepository.findByDeportistaAndEstado("1", EstadosPlanEntrenamientoEnum.ACTIVO)).thenReturn(null);

		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.getPlanesEntrenamientoPorEntrenador("1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encontraron PlanEntrenamientos para el entrenador", ex.getMessage());

	}

	@Test
	void registrarPlanEntrenamientoUsuarioNotFound() {
		log.info("Inicio test registrarPlanEntrenamientoUsuarioNotFound");
		PlanEntrenamientoIn planEntrenamiento = new PlanEntrenamientoIn();
		planEntrenamiento.setNombre("prueba");

		Optional<UsuarioEntity> optUsuario = Optional.empty();

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.registrarPlanEntrenamiento(planEntrenamiento, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un usuario registrado", ex.getMessage());
	}
	
	@Test
	void registrarPlanEntrenamientoSocioNegocioNotFound() {
		log.info("Inicio test registrarPlanEntrenamientoSocioNegocioNotFound");
		PlanEntrenamientoIn planEntrenamiento = new PlanEntrenamientoIn();
		planEntrenamiento.setNombre("prueba");

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(null);
		
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.registrarPlanEntrenamiento(planEntrenamiento, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un socio de negocio registrado para el usuario", ex.getMessage());
	}
	
	@Test
	void registrarPlanEntrenamientoDeporteNotFound() {
		log.info("Inicio test registrarPlanEntrenamientoDeporteNotFound");
		PlanEntrenamientoIn planEntrenamiento = new PlanEntrenamientoIn();
		planEntrenamiento.setNombre("prueba");
		planEntrenamiento.setIdDeporte(1l);

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);
		SocioNegocioEntity socio = new SocioNegocioEntity();
		socio.setId("1");
		socio.setUsuario(usuario);
		Optional<DeporteEntity> optDeporte = Optional.empty();

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(socio);
		Mockito.when(deporteRepository.findById(1l)).thenReturn(optDeporte);
		
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.registrarPlanEntrenamiento(planEntrenamiento, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un deporte registrado", ex.getMessage());
	}
	
	@Test
	void registrarPlanEntrenamientoDeportistaNotFound() {
		log.info("Inicio test registrarPlanEntrenamientoDeportistaNotFound");
		PlanEntrenamientoIn planEntrenamiento = new PlanEntrenamientoIn();
		planEntrenamiento.setNombre("prueba");
		planEntrenamiento.setIdDeporte(1l);
		planEntrenamiento.setIdDeportista("1");

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);
		SocioNegocioEntity socio = new SocioNegocioEntity();
		socio.setId("1");
		socio.setUsuario(usuario);
		DeporteEntity deporte = new DeporteEntity();
		deporte.setId(1l);
		Optional<DeporteEntity> optDeporte = Optional.of(deporte);
		Optional<DeportistaEntity> optDeportista = Optional.empty();

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(socio);
		Mockito.when(deporteRepository.findById(1l)).thenReturn(optDeporte);
		Mockito.when(deportistaRepository.findById("1")).thenReturn(optDeportista);
		
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.registrarPlanEntrenamiento(planEntrenamiento, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un deportista registrado", ex.getMessage());
	}
	
	@Test
	void registrarPlanEntrenamientoEjercicioNotFound() {
		log.info("Inicio test registrarPlanEntrenamientoEjercicioNotFound");
		PlanEntrenamientoIn planEntrenamiento = new PlanEntrenamientoIn();
		planEntrenamiento.setNombre("prueba");
		planEntrenamiento.setIdDeporte(1l);
		planEntrenamiento.setIdDeportista("1");
		planEntrenamiento.setEjercicios(Arrays.asList("1"));

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);
		SocioNegocioEntity socio = new SocioNegocioEntity();
		socio.setId("1");
		socio.setUsuario(usuario);
		DeporteEntity deporte = new DeporteEntity();
		deporte.setId(1l);
		Optional<DeporteEntity> optDeporte = Optional.of(deporte);
		DeportistaEntity deportista = new DeportistaEntity();
		deportista.setId("1");
		Optional<DeportistaEntity> optDeportista = Optional.of(deportista);

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(socio);
		Mockito.when(deporteRepository.findById(1l)).thenReturn(optDeporte);
		Mockito.when(deportistaRepository.findById("1")).thenReturn(optDeportista);
		Mockito.when(ejercicioRepository.findByIdAndEstado("1", EstadosEjercicioEnum.ACTIVO)).thenReturn(null);
		
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			planEntrenamientoService.registrarPlanEntrenamiento(planEntrenamiento, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un ejercicio activo registrado para el id enviado", ex.getMessage());
	}
	
	@Test
	void registrarPlanEntrenamiento() {
		log.info("Inicio test registrarPlanEntrenamiento");
		PlanEntrenamientoIn planEntrenamiento = new PlanEntrenamientoIn();
		planEntrenamiento.setNombre("prueba");
		planEntrenamiento.setIdDeporte(1l);
		planEntrenamiento.setIdDeportista("1");
		planEntrenamiento.setEjercicios(Arrays.asList("1"));

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);
		SocioNegocioEntity socio = new SocioNegocioEntity();
		socio.setId("1");
		socio.setUsuario(usuario);
		DeporteEntity deporte = new DeporteEntity();
		deporte.setId(1l);
		Optional<DeporteEntity> optDeporte = Optional.of(deporte);
		DeportistaEntity deportista = new DeportistaEntity();
		deportista.setId("1");
		Optional<DeportistaEntity> optDeportista = Optional.of(deportista);
		EjercicioEntity ejercicio = new EjercicioEntity();
		ejercicio.setId("1");

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(socio);
		Mockito.when(deporteRepository.findById(1l)).thenReturn(optDeporte);
		Mockito.when(deportistaRepository.findById("1")).thenReturn(optDeportista);
		Mockito.when(ejercicioRepository.findByIdAndEstado("1", EstadosEjercicioEnum.ACTIVO)).thenReturn(ejercicio);
		
		try {
			PlanEntrenamientoOut respuesta = planEntrenamientoService.registrarPlanEntrenamiento(planEntrenamiento, "1");
			Assertions.assertEquals(planEntrenamiento.getNombre(), respuesta.getNombre());
		} catch (ElementoNoEncontradoException e) {
			log.error(e.getLocalizedMessage());
			Assertions.assertTrue(false);
		}

	}

}
