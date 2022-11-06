package co.edu.uniandes.planafiliacion.service;

import java.util.List;

import co.edu.uniandes.planafiliacion.dto.Ejercicio;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;

/**
 * Interfaz con todos los métodos disponibles para gestionar Ejercicios
 *
 * @since 1.0.0
 */
public interface IEjercicioService {

  /**
   * Método encargado de obtener Ejercicio por ID
   *
   * @param id Identificador único del Ejercicio
   * @return Información del Ejercicio
   * @throws ElementoNoEncontradoException Error cuando no se puede obtener el Ejercicio
   * @since 1.0.0
   */
  public Ejercicio getEjercicioById(String id) throws ElementoNoEncontradoException;

  /**
   * Método encargado de listar Ejercicios
   *
   * @return Lista de Ejercicios
   * @throws ElementoNoEncontradoException Error cuando no se pueden consultar los Ejercicios
   * @since 1.0.0
   */
  public List<Ejercicio> getEjercicios() throws ElementoNoEncontradoException;
  
  /**
   * Método encargado de guardar Ejercicio
   *
   * @param ejercicio
   * @return Información del Ejercicio
   * @since 1.0.0
   */
  public Ejercicio registrarEjercicio(Ejercicio ejercicio, String usuario) throws ElementoNoEncontradoException;

  /**
   * Método encargado de inactivar Ejercicio por ID
   *
   * @param id Identificador único del Ejercicio
   * @return Información del Ejercicio
   * @throws ElementoNoEncontradoException Error cuando no se puede obtener el Ejercicio
   * @since 1.0.0
   */
  public void inactivarEjercicio(String id) throws ElementoNoEncontradoException;
  
}
