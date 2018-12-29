package com.saguadopro.gestionapartamentos.repositories;

import com.saguadopro.gestionapartamentos.entities.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio que conecta con la Base de Datos de Huespedes
 */
public interface HuespedRepo extends JpaRepository<Huesped,Long> {

}