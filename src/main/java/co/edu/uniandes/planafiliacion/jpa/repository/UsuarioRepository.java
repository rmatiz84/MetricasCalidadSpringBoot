package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.planafiliacion.jpa.entity.UsuarioEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad usuario
 *
 * @since 1.0.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
	
	public UsuarioEntity findByUsername(String username);
	
	public UsuarioEntity findByEmail(String email);

}
