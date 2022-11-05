package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.commons.jpa.entity.afiliaciones.PlanAdquiridoEntity;
import co.edu.uniandes.commons.jpa.entity.usuarios.DeportistaEntity;
import co.edu.uniandes.commons.util.EstadosPlanAdquiridoEnum;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad departamento
 *
 * @since 1.0.0
 */
@Repository
public interface PlanAdquiridoRepository extends JpaRepository<PlanAdquiridoEntity, String> {
	
	public PlanAdquiridoEntity findByDeportistaAndEstado(DeportistaEntity deportista, EstadosPlanAdquiridoEnum estado);

}
