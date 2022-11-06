package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad PERFIL_DEMOGRAFICO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "PERFIL_DEMOGRAFICO")
@NoArgsConstructor
@Getter
@Setter
public class PerfilDemograficoEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @Column(name = "PERFILDEMO_ID", length = 36)
  private String id;
  
  @OneToOne(optional = false)
  @JoinColumn(name = "DEPORTISTA_ID", referencedColumnName = "DEPORTISTA_ID")
  private DeportistaEntity deportista;
  
  @Column(name = "PERFILDEMO_FECHA_NACIMIENTO", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date fechaNacimiento;
  
  @Column(name = "PERFILDEMO_GENERO", length = 1, nullable = true)
  private String genero;
  
  @Column(name = "PERFILDEMO_GRUPO_SANGUINEO", length = 5, nullable = true)
  private String grupoSanguineo;

  @Column(name = "PERFILDEMO_PESO",  nullable = true)
  private Double peso;
  
  @Column(name = "PERFILDEMO_ESTATURA",  nullable = true)
  private Double estatura;
  
//  @ManyToOne(optional = false, fetch = FetchType.EAGER)
//  @JoinColumn(name = "PAIS_ID_RESIDENCIA", referencedColumnName = "PAIS_ID")
//  private PaisEntity paisResidencia;
  
  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "CIUDAD_ID_NACIMIENTO", referencedColumnName = "CIUDAD_ID")
  private CiudadEntity ciudadNacimiento;
  
  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "CIUDAD_ID_RESIDENCIA", referencedColumnName = "CIUDAD_ID")
  private CiudadEntity ciudadResidencia;

  @Column(name = "PERFILDEMO_RESIDE_DESDE", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date resideDesde;
  
  @Column(name = "PERFILDEMO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  


}
