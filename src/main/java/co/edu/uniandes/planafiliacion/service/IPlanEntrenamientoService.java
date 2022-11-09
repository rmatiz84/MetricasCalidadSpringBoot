package co.edu.uniandes.planafiliacion.service;

import java.util.List;

import co.edu.uniandes.planafiliacion.dto.PlanEntrenamientoIn;
import co.edu.uniandes.planafiliacion.dto.PlanEntrenamientoOut;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;


/**
 * Interfaz con todos los métodos disponibles para gestionar PlanEntrenamientos
 *
 * @since 1.0.0
 */
public interface IPlanEntrenamientoService {

  /**
   * Método encargado de obtener PlanEntrenamiento por ID
   *
   * @param id Identificador único del PlanEntrenamiento
   * @return Información del PlanEntrenamiento
   * @throws ElementoNoEncontradoException Error cuando no se puede obtener el PlanEntrenamiento
   * @since 1.0.0
   */
  public PlanEntrenamientoOut getPlanEntrenamientoById(String id);
  
  /**
   * Método encargado de obtener PlanEntrenamiento por ID
   *
   * @param id Identificador único del PlanEntrenamiento
   * @return Información del PlanEntrenamiento
   * @throws ElementoNoEncontradoException Error cuando no se puede obtener el PlanEntrenamiento
   * @since 1.0.0
   */
  public PlanEntrenamientoOut getPlanEntrenamientoDeportista(String idDeportista) throws ElementoNoEncontradoException;

  /**
   * Método encargado de listar PlanEntrenamientos
   *
   * @return Lista de PlanEntrenamientos
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los PlanEntrenamientos
   * @since 1.0.0
   */
  public List<PlanEntrenamientoOut> getPlanesEntrenamientoPorEntrenador(String idEntrenador) throws ElementoNoEncontradoException;
  
  /**
   * Método encargado de guardar PlanEntrenamiento
   *
   * @param PlanEntrenamiento
   * @return Información del PlanEntrenamiento
   * @since 1.0.0
   */
  public PlanEntrenamientoOut registrarPlanEntrenamiento(PlanEntrenamientoIn PlanEntrenamiento, String usuario) throws ElementoNoEncontradoException;

  
  
}
