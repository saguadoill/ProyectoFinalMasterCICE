package com.saguadopro.gestionapartamentos.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que define al propietario de un apartamento o inmueble
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "propietarios")
public class Propietario {

    /**
     * ID o numero de identificacion del propietario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario", nullable = false, unique = true)
    private Integer idPropietario;

    /**
     * Nombre del propietario
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellidos del propietario
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Numero de telefono de contacto del propietario
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Correo electronico del propietario
     */
    @Column(name = "email")
    private String email;

}
