package com.saguadopro.gestionusuarios.repositories;

import com.saguadopro.gestionusuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repositorio de Usuarios
 */
@Repository
public interface UsuariosRepo extends JpaRepository<Usuario,Long> {

    /**
     * Busca un Usuario a partir del username o nombre de usuario
     * @param username - Nombre de usuario
     * @return - Lista de Entidades de tipo Usuario
     */
    @Query(value = "SELECT * FROM usuarios WHERE username = :username", nativeQuery = true)
    List<Usuario> encontrarUsuario(@Param("username") String username);

    /**
     * Buscar un usuario por el Id
     * @param id - Id o numero de usuario
     * @return - Lista de Entidades de tipo Usuario
     */
    @Query(value = "SELECT * FROM usuarios WHERE id_usuario = :id", nativeQuery = true)
    List<Usuario> encontrarUsuarioById(@Param("id") Long id);

    /**
     * Modifica el password o contraseña del usuario
     * @param passwd - Password o contraseña nueva
     * @param id - Id del usuario al que hay que cambiar la constraseña o password
     * @return - >0 true, <0 false
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET passwd = ? WHERE id_usuario = ?", nativeQuery = true)
    Integer cambiarPasswd( String passwd, Long id);

}

