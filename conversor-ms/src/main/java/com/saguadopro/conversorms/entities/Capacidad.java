package com.saguadopro.conversorms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que defina la capacidad de personas que pueden usar un apartamento. Ejm: ..2PAX...4PAX...
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capacidad {

    /**
     * Id o numero identificativo de la Capacidad
     */
    private Integer idCapacidad;

    /**
     * Maximo numero de personas que admine el apartamento.
     */
    private String maxPersonas;

}
