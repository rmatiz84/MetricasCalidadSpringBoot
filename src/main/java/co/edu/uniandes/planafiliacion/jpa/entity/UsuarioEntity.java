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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad USUARIO
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity implements Serializable{

  private static final long serialVersionUID = -9150490479472589528L;

  @Id
  @Column(name = "USUARIO_ID", length = 36)
  private String id;
  
  @NotNull
  @Column(name = "USUARIO_USERNAME", unique = true, length = 20, nullable = false)
  private String username;

  @Column(name = "USUARIO_PASSWORD", length = 60, nullable = false)
  private String password;
  
  @Column(name = "USUARIO_ENABLED", nullable = false)
  private Boolean enabled;
  
  @Column(name = "USUARIO_EMAIL", unique = true, length = 100, nullable = false)
  private String email;

  @Column(name = "USUARIO_FECHA_REGISTRO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRegistro;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "USUARIOS_TO_ROLES", joinColumns = @JoinColumn(name="USUARIO_ID")
  , inverseJoinColumns = @JoinColumn(name="ROLE_ID")
  , uniqueConstraints = {@UniqueConstraint(columnNames = {"USUARIO_ID","ROLE_ID"})})
  private List<RoleEntity> roles;
  

}
