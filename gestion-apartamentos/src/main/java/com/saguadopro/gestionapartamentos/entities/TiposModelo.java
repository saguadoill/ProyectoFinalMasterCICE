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
public class TiposModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipomodelo", nullable = false, unique = true)
    private Integer idTipoModelo;

    @Column(name = "tipo", unique = true)
    private String tipo;

}
