package com.saguadopro.gestioreservas.repositories;

import com.saguadopro.gestioreservas.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que conecta con la BBDD de las Reservas
 */
@Repository
public interface ReservasRepo extends JpaRepository<Reserva,Long> {

}
