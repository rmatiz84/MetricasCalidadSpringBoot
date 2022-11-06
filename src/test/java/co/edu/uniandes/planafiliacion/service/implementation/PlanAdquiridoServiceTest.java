package co.edu.uniandes.planafiliacion.service.implementation;

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

import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoIn;
import co.edu.uniandes.planafiliacion.dto.PlanAdquiridoOut;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.jpa.entity.DeportistaEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.PlanSuscripcionEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.UsuarioEntity;
import co.edu.uniandes.planafiliacion.jpa.repository.DeportistaRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.PlanAdquiridoRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.PlanSuscripcionRepository;
import co.edu.uniandes.planafiliacion.jpa.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlanAdquiridoServiceTest {

  @Spy private ModelMapper modelMapper;

  @Mock private PlanAdquiridoRepository planAdquiridoRepository;
  @Mock private UsuarioRepository usuarioRepository;
  @Mock private DeportistaRepository deportistaRepository;
  @Mock private PlanSuscripcionRepository planSuscripcionRepository;

  @InjectMocks private PlanAdquiridoService planAdquiridoService;
  
  @Test
  void registrarPlanUsuarioNotFound() {
    log.info("Inicio test registrarPlanUsuarioNotFound");
    PlanAdquiridoIn plan = new PlanAdquiridoIn();
    plan.setIdDeportista("1");
    plan.setIdPlanSuscripcion(1L);
    Optional<UsuarioEntity> optUsuario = Optional.empty();
    
    Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
    ElementoNoEncontradoException ex =
            Assertions.assertThrows(
                    ElementoNoEncontradoException.class,
                    () -> {
                    	planAdquiridoService.registrarPlanUsuario(plan);
                    });
    Assertions.assertNotNull(ex.getMessage());
    Assertions.assertEquals("No se encuentra un usuario registrado", ex.getMessage());
  }
  
  @Test
  void registrarPlanAdquiridoDeportista() {
    log.info("Inicio test registrarPlanAdquiridoDeportista");
    PlanAdquiridoIn plan = new PlanAdquiridoIn();
    plan.setIdDeportista("1");
    plan.setIdPlanSuscripcion(1L);
    UsuarioEntity usuario = new UsuarioEntity();
    usuario.setId("1");
    DeportistaEntity deportista = new DeportistaEntity();
    deportista.setId("1");
    deportista.setUsuario(usuario);
    Optional<UsuarioEntity> optUsuario = Optional.of(usuario);
    PlanSuscripcionEntity planSus = new PlanSuscripcionEntity();
    planSus.setId(1l);
    Optional<PlanSuscripcionEntity> optPlan = Optional.of(planSus);
    
    Mockito.when(usuarioRepository.findById("1")).thenReturn(optUsuario);
    Mockito.when(deportistaRepository.findByUsuario(optUsuario.get())).thenReturn(deportista);
    Mockito.when(planSuscripcionRepository.findById(1l)).thenReturn(optPlan);
    
    PlanAdquiridoOut planAdquirido;
	try {
		planAdquirido = planAdquiridoService.registrarPlanUsuario(plan);
		Assertions.assertEquals(deportista.getId(), planAdquirido.getIdDeportista());
	} catch (ElementoNoEncontradoException e) {
		log.error(e.getLocalizedMessage());
	}
    
  }
  

 
}
