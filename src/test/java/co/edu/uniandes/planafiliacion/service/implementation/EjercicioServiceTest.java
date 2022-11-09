package co.edu.uniandes.planafiliacion.service.implementation;

import java.util.ArrayList;
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

import co.edu.uniandes.planafiliacion.dto.Ejercicio;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.jpa.entity.EjercicioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.SocioNegocioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.UsuarioEntity;
import co.edu.uniandes.planafiliacion.jpa.repository.EjercicioRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.SocioNegocioRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.UsuarioRepository;
import co.edu.uniandes.planafiliacion.utilities.EstadosEjercicioEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EjercicioServiceTest {

	@Spy
	private ModelMapper modelMapper;

	@Mock
	private EjercicioRepository ejercicioRepository;
	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private SocioNegocioRepository socioNegocioRepository;

	@InjectMocks
	private EjercicioService ejercicioService;

	@Test
	void getEjercicioById() throws ElementoNoEncontradoException {
		log.info("Inicio test getEjercicioById");
		EjercicioEntity evento1 = new EjercicioEntity();
		evento1.setId("1");
		Optional<EjercicioEntity> opt = Optional.of(evento1);

		List<EjercicioEntity> listaUsuario = new ArrayList<>();
		listaUsuario.add(evento1);

		Mockito.when(ejercicioRepository.findById("1")).thenReturn(opt);

		Ejercicio respuesta = ejercicioService.getEjercicioById("1");
		Assertions.assertEquals(listaUsuario.get(0).getId(), respuesta.getId());
	}

	@Test
	void getEjercicioByIdNotFound() throws ElementoNoEncontradoException {
		log.info("Inicio test getEjercicioByIdNotFound");
		Optional<EjercicioEntity> opt = Optional.empty();

		Mockito.when(ejercicioRepository.findById("1")).thenReturn(opt);

		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			ejercicioService.getEjercicioById("1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encontró el Ejercicio 1", ex.getMessage());

	}

	@Test
	void getEjercicios() throws ElementoNoEncontradoException {
		log.info("Inicio test getEjercicios");

		EjercicioEntity evento1 = new EjercicioEntity();
		evento1.setId("1");
		EjercicioEntity evento2 = new EjercicioEntity();
		evento1.setId("2");

		List<EjercicioEntity> listaEventos = new ArrayList<>();
		listaEventos.add(evento1);
		listaEventos.add(evento2);

		Mockito.when(ejercicioRepository.findAll()).thenReturn(listaEventos);

		List<Ejercicio> respuesta = ejercicioService.getEjercicios();
		Assertions.assertEquals(respuesta.size(), 2);
	}

	@Test
	void getEjerciciosNotFound() throws ElementoNoEncontradoException {
		log.info("Inicio test getEjerciciosNotFound");

		List<EjercicioEntity> listaEventos = new ArrayList<>();

		Mockito.when(ejercicioRepository.findAll()).thenReturn(listaEventos);

		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			ejercicioService.getEjercicios();
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encontraron Ejercicios", ex.getMessage());

	}
	
	@Test
	void inactivarEjercicio() throws ElementoNoEncontradoException {
		log.info("Inicio test inactivarEjercicio");
		EjercicioEntity evento1 = new EjercicioEntity();
		evento1.setId("1");
		evento1.setEstado(EstadosEjercicioEnum.ACTIVO);
		Optional<EjercicioEntity> opt = Optional.of(evento1);

		List<EjercicioEntity> listaUsuario = new ArrayList<>();
		listaUsuario.add(evento1);

		Mockito.when(ejercicioRepository.findById("1")).thenReturn(opt);

		ejercicioService.inactivarEjercicio("1");
		Assertions.assertEquals(evento1.getEstado().name(), EstadosEjercicioEnum.INACTIVO.name());
	}

	@Test
	void inactivarEjercicioNotFound() throws ElementoNoEncontradoException {
		log.info("Inicio test inactivarEjercicioNotFound");
		Optional<EjercicioEntity> opt = Optional.empty();

		Mockito.when(ejercicioRepository.findById("1")).thenReturn(opt);

		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			ejercicioService.inactivarEjercicio("1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encontró el Ejercicio 1", ex.getMessage());

	}
	
	@Test
	void registrarEjercicioUsuarioNotFound() {
		log.info("Inicio test registrarEjercicioUsuarioNotFound");
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setNombre("prueba");

		Optional<UsuarioEntity> optUsuario = Optional.empty();

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			ejercicioService.registrarEjercicio(ejercicio, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un usuario registrado", ex.getMessage());
	}
	
	@Test
	void registrarEjercicioSocioNegocioNotFound() {
		log.info("Inicio test registrarEjercicioSocioNegocioNotFound");
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setNombre("prueba");

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(null);
		
		ElementoNoEncontradoException ex = Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
			ejercicioService.registrarEjercicio(ejercicio, "1");
		});
		Assertions.assertNotNull(ex.getMessage());
		Assertions.assertEquals("No se encuentra un socio de negocio registrado para el usuario", ex.getMessage());
	}
	
	@Test
	void registrarEjercicio() {
		log.info("Inicio test registrarEjercicio");
		Ejercicio ejercicio = new Ejercicio();
		ejercicio.setNombre("prueba");

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId("1");
		Optional<UsuarioEntity> optUsuario = Optional.of(usuario);
		SocioNegocioEntity socio = new SocioNegocioEntity();
		socio.setId("1");
		socio.setUsuario(usuario);

		Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
		Mockito.when(socioNegocioRepository.findByUsuario(optUsuario.get())).thenReturn(socio);
		
		try {
			Ejercicio respuesta = ejercicioService.registrarEjercicio(ejercicio, "1");
			Assertions.assertEquals(ejercicio.getNombre(), respuesta.getNombre());
		} catch (ElementoNoEncontradoException e) {
			log.error(e.getLocalizedMessage());
			Assertions.assertTrue(false);
		}

		
	}

}
