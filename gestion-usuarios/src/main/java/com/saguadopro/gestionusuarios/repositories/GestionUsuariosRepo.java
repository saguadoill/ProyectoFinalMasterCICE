package com.saguadopro.gestionusuarios.repositories;

import com.saguadopro.gestionusuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GestionUsuariosRepo extends JpaRepository<Usuario,Long> {

    @Query(value = "SELECT * FROM usuarios WHERE username = :username", nativeQuery = true)
    List<Usuario> encontrarUsuario(@Param("username") String username);

    @Query(value = "SELECT * FROM usuarios WHERE id_usuario = :id", nativeQuery = true)
    List<Usuario> encontrarUsuarioById(@Param("id") Long id);

    @Query(value = "SELECT cambio_passwd FROM usuarios WHERE id_usuario = :id", nativeQuery = true)
    Boolean verificarCambioPasswd(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET passwd = ? WHERE id_usuario = ?", nativeQuery = true)
    Integer cambiarPasswd( String passwd, Long id);

    @Query(value = "SELECT foto_url FROM usuarios WHERE id_usuario = :id", nativeQuery = true)
    String getFotoUrl(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET foto_url = ? WHERE id_usuario = ?", nativeQuery = true)
    Integer setFotoUrl( String foto_url, Long id);
}

