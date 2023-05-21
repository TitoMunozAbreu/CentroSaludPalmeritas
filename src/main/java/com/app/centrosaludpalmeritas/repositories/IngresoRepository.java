package com.app.centrosaludpalmeritas.repositories;

import com.app.centrosaludpalmeritas.entities.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz IngresoRepository
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de persistencia de datos
 */
@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
}
