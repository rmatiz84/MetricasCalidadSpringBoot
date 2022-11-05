package co.edu.uniandes.planafiliacion;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase principal que permite iniciar el microservicio con el framework Spring Boot
 *
 * @since 1.0.0
 */
@EntityScan({"co.edu.uniandes.commons.jpa.entity"})
@EnableFeignClients
@SpringBootApplication
public class PlanAfiliacionMSApplication {

  @Bean
  public ModelMapper modelMapper() { return new ModelMapper(); }

  /**
   * Método principal que inicializa la aplicación Spring Boot
   *
   * @param args Argumentos de entrada para iniciar la aplicación de Spring Boot
   * @since 1.0.0
   */
  public static void main(String[] args) {
    SpringApplication.run(PlanAfiliacionMSApplication.class, args);
  }
  
  @Bean
  public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurer() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
        	  registry.addMapping("/**")
              .allowedOrigins("*");
          }
      };
  }  

}
