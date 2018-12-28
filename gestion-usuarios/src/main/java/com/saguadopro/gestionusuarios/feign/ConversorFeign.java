package com.saguadopro.gestionusuarios.feign;


import com.saguadopro.gestionusuarios.entities.Perfil;
import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.rest.dto.PerfilDTO;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Repositorio Feign para la conversion de Entidades a Dtos y viceversa.
 * @see "ConversorMsApplication"
 */
@Repository
@FeignClient("conversor-ms")
public interface ConversorFeign {

    //DTO to ENTIY
    @RequestMapping(value = "/conversor/usuario/dto-entity", method = RequestMethod.POST)
    Usuario usuarioDtoToEntity(@RequestBody UsuarioDTO usuarioDTO);

    @RequestMapping(value = "/conversor/perfil/dto-entity",method = RequestMethod.POST)
    Perfil perfilEntityToDto(@RequestBody PerfilDTO perfilDTO);


    //ENTITY TO DTO
    @RequestMapping(value = "/conversor/usuario/entity-dto", method = RequestMethod.POST)
    UsuarioDTO usuarioEntityToDto(@RequestBody Usuario usuario);

    @RequestMapping(value = "/conversor/perfil/entity-dto",method = RequestMethod.POST)
    PerfilDTO perfilEntityToDto(@RequestBody Perfil perfil);
}
