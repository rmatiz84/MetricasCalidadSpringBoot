package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad SERVICIO_APP
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "SERVICIO_APP")
@NoArgsConstructor
@Getter
@Setter
public class ServicioAppEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SERVICIOAPP_ID", length = 15)
  private Long id;
  
  @Column(name = "SERVICIOAPP_NOMBRE", nullable = false, unique = true)
  private String nombre;
  
  @Column(name = "SERVICIOAPP_DESCRIPCION", nullable = false)
  private String descripcion;
  

}
