package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.commons.jpa.entity.usuarios.DeportistaEntity;
import co.edu.uniandes.commons.jpa.entity.usuarios.UsuarioEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad usuario
 *
 * @since 1.0.0
 */
@Repository
public interface DeportistaRepository extends JpaRepository<DeportistaEntity, String> {
	
	public DeportistaEntity findByUsuario(UsuarioEntity usuario);
	
}
