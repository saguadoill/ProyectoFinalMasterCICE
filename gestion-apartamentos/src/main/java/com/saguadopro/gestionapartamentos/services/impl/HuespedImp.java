package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;

import java.util.List;

/**
 * Interface para la gestion de Huespedes
 * @see {@link com.saguadopro.gestionapartamentos.services.HuespedService}
 */
public interface HuespedImp {

    Boolean crearHuesped(HuespedDTO huespedDTO);

    Boolean eliminarHuesped(Long idHuesped);

    Boolean modificarHuesped(HuespedDTO huespedDTO);

    HuespedDTO buscarHuesped(Long idHuesped);

    List<HuespedDTO> listaHuespedes();

}
