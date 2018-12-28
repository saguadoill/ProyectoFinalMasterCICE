package com.saguadopro.conversorms.services.impl;

import com.saguadopro.conversorms.entities.*;
import com.saguadopro.conversorms.rest.dto.*;

/**
 * Interface para manejar las conversiones de Entidade sa objetos Dto
 * @see {@link com.saguadopro.conversorms.services.EntityToDtoService}
 */
public interface EntityToDtoImpl {

    UsuarioDTO usuarioEntityToDto(Usuario usuario);

    PerfilDTO perfilEntityToDto(Perfil perfil);

    ApartamentoDTO apartamentoEntityToDto(Apartamento apartamento);

    CapacidadDTO capacidadEntityToDto(Capacidad capacidad);

    HuespedDTO huespedEntityToDto(Huesped huesped);

    PropietarioDTO propietarioEntityToDton(Propietario propietario);

    ReservaDTO reservaEntityToDto(Reserva reserva);

}
