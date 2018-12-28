package com.saguadopro.gestionusuarios.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que define los roles o perfiles de acceso a la aplicacion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfiles")
public class Perfil {

    /**
     * ID o numero de identificación del perfil
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil", nullable = false, unique = true)
    private Integer idPerfil;

    /**
     * Nombre del perfil
     */
    @Column(name = "nombre_perfil", unique = true)
    private String nombrePerfil;

}
