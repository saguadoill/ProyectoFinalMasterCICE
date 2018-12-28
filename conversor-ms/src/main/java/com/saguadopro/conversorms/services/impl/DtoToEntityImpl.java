package com.saguadopro.conversorms.services.impl;

import com.saguadopro.conversorms.entities.*;
import com.saguadopro.conversorms.rest.dto.*;

/**
 * Interface para manejar las converionses de objetos DTO a Entidades
 * @see {@link com.saguadopro.conversorms.services.DtoToEntityService}
 */
public interface DtoToEntityImpl {

    Usuario usuarioDtoToEntity(UsuarioDTO usuarioDTO);

    Perfil perfilDtoToPerfil(PerfilDTO perfilDTO);

    Apartamento apartamentoDtoToEntity(ApartamentoDTO apartamentoDTO);

    Capacidad capacidadDtoToEntity(CapacidadDTO capacidadDTO);

    Huesped huespedDtoToEntity(HuespedDTO huespedDTO);

    Propietario propietarioDtoToEntity(PropietarioDTO propietarioDTO);

    Reserva reservaDtoToEntity(ReservaDTO reservaDTO);

}
