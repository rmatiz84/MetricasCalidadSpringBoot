package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.planafiliacion.jpa.entity.DeporteEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad usuario
 *
 * @since 1.0.0
 */
@Repository
public interface DeporteRepository extends JpaRepository<DeporteEntity, Long> {
	

}
