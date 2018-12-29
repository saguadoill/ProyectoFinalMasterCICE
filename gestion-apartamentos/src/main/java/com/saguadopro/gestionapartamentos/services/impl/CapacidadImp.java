package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;

import java.util.List;

/**
 * Interface para la gestion de Capacidades
 * @see {@link com.saguadopro.gestionapartamentos.services.CapacidadService}
 */
public interface CapacidadImp {


    Boolean crearCapacidad(CapacidadDTO capacidadDTO);

    Boolean eliminarCapacidad(Integer idCapacidad);

    Boolean modificarCapacidad(CapacidadDTO capacidadDTO);

    CapacidadDTO buscarPorCapacidad(Integer idCapacidad);

    List<CapacidadDTO> listaCapacidades();

}
