package co.edu.uniandes.planafiliacion;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase encargada de implementar la interfaz SpringBootServletInitializer para desplegar las
 * aplicaciones en un servidor.
 *
 * @since 1.0.0
 */
public class PlanAfiliacionMSInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(PlanAfiliacionMSApplication.class);
  }

}
