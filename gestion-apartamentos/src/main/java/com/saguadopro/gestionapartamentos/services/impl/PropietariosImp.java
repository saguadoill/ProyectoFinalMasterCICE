package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;

import java.util.List;

public interface PropietariosImp {

    Boolean crearPropietario(PropietarioDTO propietarioDTO);

    Boolean eliminarPropietario(Long idPropietario);

    Boolean modificarPropietario(PropietarioDTO propietarioDTO);

    PropietarioDTO buscarPropietario(Integer idPropietario);

    List<PropietarioDTO> listaPropietarios();

}
