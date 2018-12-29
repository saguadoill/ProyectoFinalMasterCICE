package com.saguadopro.gestionapartamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que defina a un huesped del apartamento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "huespedes")
public class Huesped {

    /**
     * Id o numero identificattivo del huesped
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_huesped", nullable = false, unique = true)
    private Long idHuesped;

    /**
     * Nombre del huesped
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellidos del huesped
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Numero de telefono de contacto del huesped
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Correo electronico del huesped
     */
    @Column(name = "email")
    private String email;

    /**
     * DNI del huesped por seguridad.
     */
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

}
