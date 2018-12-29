package com.saguadopro.gestionapartamentos.repositories;

import com.saguadopro.gestionapartamentos.entities.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Respositorio que conecta con la Base de Datos de Propietarios
 */
public interface PropietariosRepo extends JpaRepository<Propietario,Integer> {

}
