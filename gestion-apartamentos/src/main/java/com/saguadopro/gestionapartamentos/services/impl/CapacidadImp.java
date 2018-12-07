package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;

import java.util.List;

public interface CapacidadImp {


    Boolean crearCapacidad(CapacidadDTO capacidadDTO);

    Boolean eliminarCapacidad(Integer idTipoModelo);

    Boolean modificarCapacidad(CapacidadDTO capacidadDTO);

    CapacidadDTO buscarPorCapacidad(Integer idCapacidad);

    List<CapacidadDTO> listaCapacidades();

}
