package co.edu.uniandes.planafiliacion.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.planafiliacion.jpa.entity.PlanSuscripcionEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar
 * la información de la entidad departamento
 *
 * @since 1.0.0
 */
@Repository
public interface PlanSuscripcionRepository extends JpaRepository<PlanSuscripcionEntity, Long> {

}
