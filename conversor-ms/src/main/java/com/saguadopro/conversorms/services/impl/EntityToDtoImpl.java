package com.saguadopro.conversorms.services.impl;

import com.saguadopro.conversorms.entities.Perfil;
import com.saguadopro.conversorms.entities.Usuario;
import com.saguadopro.conversorms.rest.dto.PerfilDTO;
import com.saguadopro.conversorms.rest.dto.UsuarioDTO;

public interface EntityToDtoImpl {

    UsuarioDTO usuarioEntityToDto(Usuario usuario);

    PerfilDTO perfilEntityToDto(Perfil perfil);

}
