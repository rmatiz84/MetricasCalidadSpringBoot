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
 * Entidad TIPO_SOCIO_NEGOCIO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "TIPO_SOCIO_NEGOCIO")
@NoArgsConstructor
@Getter
@Setter
public class TipoSocioNegocioEntity implements Serializable{

  private static final long serialVersionUID = -3126187135051961546L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOSOCIONEGOCIO_ID", length = 15)
  private Long id;
  
  @Column(name = "TIPOSOCIONEGOCIO_NOMBRE", unique = true, length = 30)
  private String nombre;

 
}
