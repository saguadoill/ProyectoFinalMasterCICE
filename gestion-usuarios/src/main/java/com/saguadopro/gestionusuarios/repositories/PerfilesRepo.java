package com.saguadopro.gestionusuarios.repositories;

import com.saguadopro.gestionusuarios.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilesRepo extends JpaRepository<Perfil,Integer> {

    @Query(value = "SELECT * FROM perfiles WHERE nombre_perfil = :nombrePerfil", nativeQuery = true)
    Perfil buscarPerfilPorNombre(@Param("nombrePerfil") String nombrePerfil);

}
