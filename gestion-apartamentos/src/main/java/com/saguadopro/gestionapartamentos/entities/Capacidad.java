package com.saguadopro.gestionapartamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que defina la capacidad de personas que pueden usar un apartamento. Ejm: ..2PAX...4PAX...
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipos_modelos")
public class Capacidad {

    /**
     * Id o numero identificativo de la Capacidad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_capacidad", nullable = false, unique = true)
    private Integer idCapacidad;

    /**
     * Maximo numero de personas que admine el apartamento.
     */
    @Column(name = "capacidad", unique = true)
    private String maxPersonas;

}
