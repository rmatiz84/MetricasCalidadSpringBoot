package co.edu.uniandes.planafiliacion.exception;

/**
 * La clase ErrorGeneralException representa errores genéricos de la aplicación
 *
 * @since 1.0.0
 */
public class ErrorGeneralException extends Exception {

  private static final long serialVersionUID = 3871012184673680498L;

  /**
   * Construye una nueva excepción con un mensaje de descripción
   *
   * @param mensaje Descripción del error
   * @since 1.0.0
   */
  public ErrorGeneralException(String mensaje) {
    super(mensaje);
  }

  /**
   * Construye un nuevo error general a partir del objeto Throwable
   *
   * @param cause Error
   * @since 1.0.0
   */
  public ErrorGeneralException(Throwable cause) {
    super(cause);
  }

}
