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

import co.edu.uniandes.planafiliacion.utilities.EstadosPlanAdquiridoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad PLAN_ADQUIRIDO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "PLAN_ADQUIRIDO")
@NoArgsConstructor
@Getter
@Setter
public class PlanAdquiridoEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @Column(name = "PLANADQUIRIDO_ID", length = 36)
  private String id;
  
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "DEPORTISTA_ID", referencedColumnName = "DEPORTISTA_ID")
  private DeportistaEntity deportista;
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "PLANSUSCRIPCION_ID", referencedColumnName = "PLANSUSCRIPCION_ID")
  private PlanSuscripcionEntity planSuscripcion;
  
  @Column(name = "PLANADQUIRIDO_ESTADO", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private EstadosPlanAdquiridoEnum estado; 
  
  @Column(name = "PLANADQUIRIDO_INICIO_VIGENCIA", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaInicioVigencia;
  
  @Column(name = "PLANADQUIRIDO_FIN_VIGENCIA", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaFinVigencia;
  
  @Column(name = "PLANADQUIRIDO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  

}
