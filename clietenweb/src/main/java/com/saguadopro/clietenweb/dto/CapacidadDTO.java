package com.saguadopro.clietenweb.dto;

import lombok.Data;

/**
 * POJO que defina la capacidad de personas que pueden usar un apartamento. Ejm: ..2PAX...4PAX...
 */
@Data
public class CapacidadDTO {

    /**
     * Id o numero identificativo de la Capacidad
     */
    private Integer idCapacidad;

    /**
     * Maximo numero de personas que admine el apartamento.
     */
    private String maxPersonas;

}
