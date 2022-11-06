package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad SOCIO_NEGOCIO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "SOCIO_NEGOCIO")
@NoArgsConstructor
@Getter
@Setter
public class SocioNegocioEntity implements Serializable{

  private static final long serialVersionUID = -9150490479472589528L;

  @Id
  @Column(name = "SOCIONEGOCIO_ID", length = 36)
  private String id;
  
  @Column(name = "SOCIONEGOCIO_NOMBRE", length = 100, nullable = false)
  private String nombre;

  @Column(name = "SOCIONEGOCIO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  
  @OneToOne
  @JoinColumn(name = "TIPOSOCIONEGOCIO_ID", referencedColumnName = "TIPOSOCIONEGOCIO_ID", nullable = false)
  private TipoSocioNegocioEntity tipoSocioNegocio;
  
  @OneToOne
  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", nullable = false, unique = true)
  private UsuarioEntity usuario;
  
  

}
