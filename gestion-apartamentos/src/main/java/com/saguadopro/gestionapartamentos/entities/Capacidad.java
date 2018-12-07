package com.saguadopro.gestionapartamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipos_modelos")
public class Capacidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_capacidad", nullable = false, unique = true)
    private Integer idCapacidad;

    @Column(name = "capacidad", unique = true)
    private String maxPersonas;

}
