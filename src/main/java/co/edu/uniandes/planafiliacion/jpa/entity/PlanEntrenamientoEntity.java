package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import co.edu.uniandes.planafiliacion.utilities.EstadosPlanEntrenamientoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad PLAN_ENTRENAMIENTO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "PLAN_ENTRENAMIENTO")
@NoArgsConstructor
@Getter
@Setter
public class PlanEntrenamientoEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @Column(name = "PLANENTRENAMIENTO_ID", length = 36)
  private String id;
  
  @Column(name = "PLANENTRENAMIENTO_NOMBRE", length = 100, nullable = false)
  private String nombre;
  
  @Column(name = "PLANENTRENAMIENTO_OBJETIVO", length = 250, nullable = false)
  private String objetivo;
  
  @Column(name = "PLANENTRENAMIENTO_TIEMPO_TOTAL", nullable = false)
  private Long tiempoTotal;
   
  @Column(name = "PLANENTRENAMIENTO_ESTADO", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private EstadosPlanEntrenamientoEnum estado; 
  
  @Column(name = "PLANENTRENAMIENTO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "DEPORTISTA_ID", referencedColumnName = "DEPORTISTA_ID")
  private DeportistaEntity deportista;
  
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "DEPORTE_ID", referencedColumnName = "DEPORTE_ID")
  private DeporteEntity deporte;
  
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "SOCIONEGOCIO_ID", referencedColumnName = "SOCIONEGOCIO_ID", nullable = true)
  private SocioNegocioEntity entrenador;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "EJERCICIOS_PLANENTRENAMIENTO", joinColumns = @JoinColumn(name="PLANENTRENAMIENTO_ID")
  , inverseJoinColumns = @JoinColumn(name="EJERCICIO_ID")
  , uniqueConstraints = {@UniqueConstraint(columnNames = {"PLANENTRENAMIENTO_ID","EJERCICIO_ID"})})
  private List<EjercicioEntity> ejercicios;
  

}
