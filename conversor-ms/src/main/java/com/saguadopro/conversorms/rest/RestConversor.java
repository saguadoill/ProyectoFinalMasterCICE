package com.saguadopro.conversorms.rest;

import com.saguadopro.conversorms.entities.Perfil;
import com.saguadopro.conversorms.entities.Usuario;

import com.saguadopro.conversorms.rest.dto.PerfilDTO;
import com.saguadopro.conversorms.rest.dto.UsuarioDTO;
import com.saguadopro.conversorms.services.impl.DtoToEntityImpl;
import com.saguadopro.conversorms.services.impl.EntityToDtoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/conversor")
public class RestConversor<T> {
    private Logger logger = LoggerFactory.getLogger(RestConversor.class);

    @Autowired
    DtoToEntityImpl dtoToEntity;

    @Autowired
    EntityToDtoImpl entityToDto;

    //DTO to ENTITY
    @RequestMapping(value = "/usuario/dto-entity", method = RequestMethod.POST)
    public Usuario usuarioDtoToEntity(@RequestBody UsuarioDTO usuarioDTO){
        return dtoToEntity.usuarioDtoToEntity(usuarioDTO);

    }

    @RequestMapping(value = "/perfil/dto-entity",method = RequestMethod.POST)
    public Perfil perfilDtoToEntity(@RequestBody PerfilDTO perfilDTO){
        return dtoToEntity.perfilDtoToPerfil(perfilDTO);
    }

    //ENTITY to DTO
    @RequestMapping(value = "/usuario/entity-dto", method = RequestMethod.POST)
    public UsuarioDTO usuarioEntityToDto(@RequestBody Usuario usuario){
        return entityToDto.usuarioEntityToDto(usuario);

    }

    @RequestMapping(value = "/perfil/entity-dto",method = RequestMethod.POST)
    public PerfilDTO perfilEntityToDto(@RequestBody Perfil perfil){
        return entityToDto.perfilEntityToDto(perfil);
    }


}
