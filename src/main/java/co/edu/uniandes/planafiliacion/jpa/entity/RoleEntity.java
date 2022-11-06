package co.edu.uniandes.planafiliacion.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad ROL
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity implements Serializable{

  private static final long serialVersionUID = -3126187135051961546L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROL_ID", length = 15)
  private Long id;
  
  @Column(name = "ROL_NOMBRE", unique = true, length = 30)
  private String nombre;

 
}
