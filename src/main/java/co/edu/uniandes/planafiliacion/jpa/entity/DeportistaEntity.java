package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad DEPORTISTA
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "DEPORTISTA")
@NoArgsConstructor
@Getter
@Setter
public class DeportistaEntity implements Serializable{

  private static final long serialVersionUID = -9150490479472589528L;

  @Id
  @Column(name = "DEPORTISTA_ID", length = 36)
  private String id;
  
  @Column(name = "DEPORTISTA_NOMBRE", length = 100, nullable = false)
  private String nombres;

  @Column(name = "DEPORTISTA_APELLIDO", length = 100, nullable = false)
  private String apellidos;
  
  @Column(name = "DEPORTISTA_TIPO_IDENTIFICACION", length = 1, nullable = false)
  private String tipoIdentificacion;
  
  @Column(name = "DEPORTISTA_NUM_IDENTIFICACION", length = 32, nullable = false)
  private String numeroIdentificacion;

  @Column(name = "DEPORTISTA_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  
  @OneToOne
  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", nullable = false, unique = true)
  private UsuarioEntity usuario;
  
  @OneToOne(mappedBy = "deportista", optional = true)
  private PerfilDemograficoEntity perfilDemografico;
  
  @OneToOne(mappedBy = "deportista", optional = true)
  private PerfilDeportivoEntity perfilDeportivo;
  
  @OneToOne(mappedBy = "deportista", optional = true)
  private PerfilAlimenticioEntity perfilAlimenticio;
  
  @OneToMany(mappedBy = "deportista", fetch = FetchType.LAZY)
  private List<PlanAdquiridoEntity> planesAdquiridos;

}
