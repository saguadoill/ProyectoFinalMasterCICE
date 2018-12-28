package com.saguadopro.gestionusuarios.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que define al usuario que trabajara con la aplicacion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    /**
     * ID o identificacion del usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long idUsuario;

    /**
     * Nombre de usuario
     */
    @Column(name = "username", unique = true)
    private String username;

    /**
     * Contraseña del usuario
     */
    @Column(name = "passwd")
    private String passwd;

    /**
     * Rol o perfil del usuario
     */
    @OneToOne
    @JoinColumn(name = "perfil")
    private Perfil perfil;

    /**
     * Nombre real del usuario
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellidos reales del usuario
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Indica si el usuario ha cambiado la contraseña inicial o no
     */
    @Column(name = "cambio_passwd")
    private Boolean cambioPasswd;


    /**
     * Url o direccion en disco, en donde se encuentra la Foto del usuario
     */
    @Column(name = "foto_url")
    private String foto_url;
}