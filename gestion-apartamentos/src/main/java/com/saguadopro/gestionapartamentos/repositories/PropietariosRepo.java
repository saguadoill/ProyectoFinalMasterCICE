package com.saguadopro.gestionapartamentos.repositories;

import com.saguadopro.gestionapartamentos.entities.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropietariosRepo extends JpaRepository<Propietario,Integer> {

}
