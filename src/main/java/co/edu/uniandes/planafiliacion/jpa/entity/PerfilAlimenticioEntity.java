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
 * Entidad PERFIL_ALIMENTICIO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "PERFIL_ALIMENTICIO")
@NoArgsConstructor
@Getter
@Setter
public class PerfilAlimenticioEntity implements Serializable{


  private static final long serialVersionUID = 6875241175039565420L;

  @Id
  @Column(name = "PERFILALIMEN_ID", length = 36)
  private String id;
  
  @OneToOne(optional = false)
  @JoinColumn(name = "DEPORTISTA_ID", referencedColumnName = "DEPORTISTA_ID")
  private DeportistaEntity deportista;
  
  @Column(name = "PERFILALIMEN_ALERGIAS", length = 250, nullable = true)
  private String alergias;
  
  @Column(name = "PERFILALIMEN_ALIMENTOS_PREFERIDOS",  length = 250, nullable = true)
  private String alimentosPreferidos; 
  
  @Column(name = "PERFILALIMEN_ESVEGANO",  nullable = false)
  private Boolean esVegano;
  
  @Column(name = "PERFILALIMEN_ESVEGETARIANO",  nullable = false)
  private Boolean esVegetariano;
  
  @Column(name = "PERFILALIMEN_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  


}
