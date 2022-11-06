package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.planafiliacion.jpa.entity.EjercicioEntity;
import co.edu.uniandes.planafiliacion.utilities.EstadosEjercicioEnum;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad ejercicio
 *
 * @since 1.0.0
 */
@Repository
public interface EjercicioRepository extends JpaRepository<EjercicioEntity, String> {
	
	public EjercicioEntity findByIdAndEstado(String id, EstadosEjercicioEnum estado);

}
