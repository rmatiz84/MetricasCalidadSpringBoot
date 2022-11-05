package co.edu.uniandes.planafiliacion.controller;

import co.edu.uniandes.planafiliacion.exception.ErrorGeneralException;
import co.edu.uniandes.planafiliacion.exception.ElementoNoEncontradoException;
import co.edu.uniandes.planafiliacion.dto.Mensaje;
import co.edu.uniandes.planafiliacion.dto.ValidacionError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

/**
 * Controlador encargado de gestionar los errores lanzados por la aplicación para tener una
 * respuesta estándar y escribir en logs lo ocurrido
 *
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
public class ErrorController {

  /**
   * Código resultado ERROR
   *
   * @since 1.0.0
   */
  public static final String COD_ERROR = "ERROR";

  /**
   * Código resultado FALLIDO
   *
   * @since 1.0.0
   */
  public static final String COD_FALLIDO = "FALLIDO";

  /**
   * Método encargado de atrapar errores no controlados por la aplicación y generar respuesta
   * estándar
   *
   * @param ex Error no controlado
   * @return Respuesta de error estándar
   * @since 1.0.0
   */
  @ExceptionHandler
  @ResponseBody
  public ResponseEntity<Mensaje> handleExceptionInternalServer(Exception ex) {
    log.error("INTERNAL_SERVER_ERROR", ex);
    Mensaje mensaje = new Mensaje();
    mensaje.setCodigoResultado(COD_ERROR);
    mensaje.setDescripcionRespuesta(
        "Ha ocurrido un error en el sistema: " + obtenerUltimaExcepcion(ex).getMessage());
    return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Método encargado de atrapar errores no controlados por la aplicación y generar respuesta
   * estándar
   *
   * @param ex Error no controlado
   * @return Respuesta de error estándar
   * @since 1.0.0
   */
  @ExceptionHandler
  @ResponseBody
  public ResponseEntity<Mensaje> handleExceptionInternalServer(ErrorGeneralException ex) {
    log.error("APP_ERROR", ex);
    Mensaje mensaje = new Mensaje();
    mensaje.setCodigoResultado(COD_ERROR);
    mensaje.setDescripcionRespuesta(
        "Error generico del sistema: " + obtenerUltimaExcepcion(ex).getMessage());
    return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Método encargado de generar respuestas de peticiones que no cumplen con el tipo e información
   * necesaria para ser procesadas
   *
   * @param ex Error en la información recibida de las peticiones
   * @return Respuesta de error estándar
   * @since 1.0.0
   */
  @ExceptionHandler
  @ResponseBody
  public ResponseEntity<Mensaje> handleExceptionBadRequest(MethodArgumentNotValidException ex) {
    log.error("METHOD_ARGUMENT_NOT_VALID_ERROR", ex);
    Mensaje mensaje = new Mensaje();
    mensaje.setCodigoResultado(COD_FALLIDO);
    mensaje.setDescripcionRespuesta(
        "Error al validar campos de entrada: " + obtenerUltimaExcepcion(ex).getMessage());
    mensaje.setValidaciones(ex.getBindingResult().getAllErrors().stream().map(this::mapError)
        .collect(Collectors.toList()));
    return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
  }

  /**
   * Método encargado de generar un mensaje estándar cuando la aplicación no encuentra el recurso o
   * la información solicitada en las peticiones
   *
   * @param ex Error información no encontrada
   * @return Respuesta de error estándar
   * @since 1.0.0
   */
  @ExceptionHandler
  @ResponseBody
  public ResponseEntity<Mensaje> handleExceptionNotFound(ElementoNoEncontradoException ex) {
    log.error("NOT_FOUND", ex);
    Mensaje mensaje = new Mensaje();
    mensaje.setCodigoResultado(COD_FALLIDO);
    mensaje.setDescripcionRespuesta(obtenerUltimaExcepcion(ex).getMessage());
    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
  }

  /**
   * Obtiene la última excepción o error
   *
   * @param e Objeto que representa el error o excepción de Java generada en la aplicación
   * @return Último error
   * @since 1.0.0
   */
  private static Throwable obtenerUltimaExcepcion(Throwable e) {
    if (e.getCause() != null) {
      return obtenerUltimaExcepcion(e.getCause());
    }
    return e;
  }

  /**
   * Obtiene todos los errores de validación que tienen los campos de las peticiones
   *
   * @param objectError Error por el cual un campo no cumple una validación
   * @return Modelo con información de campos y errores
   * @since 1.0.0
   */
  private ValidacionError mapError(ObjectError objectError) {
	  if (objectError instanceof FieldError) {
	    FieldError fieldError = (FieldError) objectError;
	    return new ValidacionError(fieldError.getField(), objectError.getDefaultMessage());
    }
    return new ValidacionError(objectError.getObjectName(), objectError.getDefaultMessage());
  }

}
