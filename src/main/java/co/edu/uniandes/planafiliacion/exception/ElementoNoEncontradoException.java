package co.edu.uniandes.planafiliacion.exception;

/**
 * La clase ElementoNoEncontradoException representa un error cuando se solicita algún recurso y la
 * aplicación no cuenta con información disponible para responder
 *
 * @since 1.0.0
 */
public class ElementoNoEncontradoException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Construye una nueva excepción con un mensaje de descripción
   *
   * @param mensaje Descripción del error
   * @since 1.0.0
   */
  public ElementoNoEncontradoException(String mensaje) {
    super(mensaje);
  }

}
