package co.edu.uniandes.planafiliacion.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.planafiliacion.jpa.entity.PlanEntrenamientoEntity;
import co.edu.uniandes.planafiliacion.utilities.EstadosPlanEntrenamientoEnum;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad plan entrenamiento
 *
 * @since 1.0.0
 */
@Repository
public interface PlanEntrenamientoRepository extends JpaRepository<PlanEntrenamientoEntity, String> {
	
	@Query("SELECT u FROM PlanEntrenamientoEntity u WHERE u.deportista.usuario.id = :idDeportista and u.estado = :estado")
	public List<PlanEntrenamientoEntity> findByDeportistaAndEstado(@Param("idDeportista") String idDeportista, @Param("estado") EstadosPlanEntrenamientoEnum estado);
	
	@Query("SELECT u FROM PlanEntrenamientoEntity u WHERE u.entrenador.usuario.id = :idEntrenador")
	public List<PlanEntrenamientoEntity> findByEntrenador(@Param("idEntrenador") String idEntrenador);

}
