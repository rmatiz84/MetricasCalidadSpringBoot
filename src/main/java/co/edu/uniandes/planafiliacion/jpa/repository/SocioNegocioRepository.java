package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.planafiliacion.jpa.entity.SocioNegocioEntity;
import co.edu.uniandes.planafiliacion.jpa.entity.UsuarioEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad usuario
 *
 * @since 1.0.0
 */
@Repository
public interface SocioNegocioRepository extends JpaRepository<SocioNegocioEntity, String> {
	
	public SocioNegocioEntity findByUsuario(UsuarioEntity usuario);
	
}
