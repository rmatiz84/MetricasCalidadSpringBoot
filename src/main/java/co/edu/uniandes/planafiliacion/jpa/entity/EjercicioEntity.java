package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.uniandes.planafiliacion.utilities.EstadosEjercicioEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad EJERCICIO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "EJERCICIO")
@NoArgsConstructor
@Getter
@Setter
public class EjercicioEntity implements Serializable{

  private static final long serialVersionUID = -3126187135051961546L;

  @Id
  @Column(name = "EJERCICIO_ID", length = 36)
  private String id;
  
  @Column(name = "EJERCICIO_NOMBRE", length = 100, nullable = false)
  private String nombre;
  
  @Column(name = "EJERCICIO_SERIES",  nullable = true)
  private Integer series;
  
  @Column(name = "EJERCICIO_REPETICIONES",  nullable = true)
  private Integer repeticiones;
  
  @Column(name = "EJERCICIO_DESCANSO", nullable = true)
  private Integer tiempoDescanso;
  
  @Column(name = "EJERCICIO_INSTRUCCIONES", length = 400, nullable = false)
  private String instrucciones;

  @Column(name = "EJERCICIO_IMAGEN", length = 300, nullable = true)
  private String imagen;
  
  @Column(name = "EJERCICIO_ESTADO", length = 10, nullable = false)
  @Enumerated(EnumType.STRING)
  private EstadosEjercicioEnum estado;
  
  @Column(name = "EJERCICIO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "SOCIONEGOCIO_ID", referencedColumnName = "SOCIONEGOCIO_ID", nullable = true)
  private SocioNegocioEntity entrenador;
  
  
}
