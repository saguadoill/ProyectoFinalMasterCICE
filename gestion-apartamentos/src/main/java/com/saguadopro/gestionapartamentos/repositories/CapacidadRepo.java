package com.saguadopro.gestionapartamentos.repositories;

import com.saguadopro.gestionapartamentos.entities.Capacidad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio que conecta con la Base de Datos de las Capacidades en personas de un apartamento
 */
public interface CapacidadRepo extends JpaRepository<Capacidad,Integer> {

}
