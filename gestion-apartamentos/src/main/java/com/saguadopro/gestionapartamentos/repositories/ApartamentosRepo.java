package com.saguadopro.gestionapartamentos.repositories;

import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ApartamentosRepo extends JpaRepository<Apartamento,Long> {

    @Query(value = "SELECT * FROM apartamentos WHERE id_apartamento = :id", nativeQuery = true)
    List<Apartamento> encontrarApartamentosPorId(@Param("id") Long idApartamento);

}

//    SELECT APARTAMENTOS.*, NOMBRE, APELLIDOS, EMAIL, TELEFONO FROM APARTAMENTOS JOIN PROPIETARIOS WHERE APARTAMENTOS.ID_PROPIETARIO = PROPIETARIOS.ID_PROPIETARIO;
