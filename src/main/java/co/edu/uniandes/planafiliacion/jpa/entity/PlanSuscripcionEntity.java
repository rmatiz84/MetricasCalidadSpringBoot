package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import co.edu.uniandes.planafiliacion.utilities.TipoPagoSuscripcionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad PLAN_SUSCRIPCION
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "PLAN_SUSCRIPCION")
@NoArgsConstructor
@Getter
@Setter
public class PlanSuscripcionEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PLANSUSCRIPCION_ID", length = 15)
  private Long id;
  
  @Column(name = "PLANSUSCRIPCION_NOMBRE", nullable = false, unique = true)
  private String nombre;
  
  @Column(name = "PLANSUSCRIPCION_DESCRIPCION", length = 250, nullable = false)
  private String descripcion;

  @Column(name = "PLANSUSCRIPCION_TIPOPAGO", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private TipoPagoSuscripcionEnum tipoPago;   
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "SERVICIO_PLAN_SUSCRIPCION", joinColumns = @JoinColumn(name="PLANSUSCRIPCION_ID")
  , inverseJoinColumns = @JoinColumn(name="SERVICIOAPP_ID")
  , uniqueConstraints = {@UniqueConstraint(columnNames = {"PLANSUSCRIPCION_ID","SERVICIOAPP_ID"})})
  private List<ServicioAppEntity> servicios;
  


}
