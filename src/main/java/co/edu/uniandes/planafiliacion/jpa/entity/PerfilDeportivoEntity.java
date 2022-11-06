package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad PERFIL_DEPORTIVO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "PERFIL_DEPORTIVO")
@NoArgsConstructor
@Getter
@Setter
public class PerfilDeportivoEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @Column(name = "PERFILDEPOR_ID", length = 36)
  private String id;
  
  @OneToOne(optional = false)
  @JoinColumn(name = "DEPORTISTA_ID", referencedColumnName = "DEPORTISTA_ID")
  private DeportistaEntity deportista;
  
  @Column(name = "PERFILDEMO_TIEMPOPRACTICASEM", nullable = true)
  private Long tiempoPractica;
  
  @Column(name = "PERFILDEMO_ANTECEDENTES", length = 250, nullable = true)
  private String antecedentes;

  @Column(name = "PERFILDEMO_LESIONES",  length = 250, nullable = true)
  private String lesiones;
  
  @Column(name = "PERFILDEMO_INCAPACIDADES",  length = 250, nullable = true)
  private String incapacidades; 
  
  @Column(name = "PERFILDEMO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "DEPORTES_PRACTICA", joinColumns = @JoinColumn(name="PERFILDEPOR_ID")
  , inverseJoinColumns = @JoinColumn(name="DEPORTE_ID")
  , uniqueConstraints = {@UniqueConstraint(columnNames = {"PERFILDEPOR_ID","DEPORTE_ID"})})
  private List<DeporteEntity> deportes;
  


}
