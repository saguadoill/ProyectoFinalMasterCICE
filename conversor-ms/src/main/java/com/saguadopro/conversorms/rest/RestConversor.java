package com.saguadopro.conversorms.rest;

import com.saguadopro.conversorms.entities.*;

import com.saguadopro.conversorms.rest.dto.*;
import com.saguadopro.conversorms.services.impl.DtoToEntityImpl;
import com.saguadopro.conversorms.services.impl.EntityToDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Clase Controladora de los endpoints del microservicio encagrgado de las conversiones de Entity a DTO y viceversa
 * @see {@link com.saguadopro.conversorms.services.DtoToEntityService}
 * @see {@link com.saguadopro.conversorms.services.EntityToDtoService}
 */
@RestController
@RequestMapping(value = "/conversor")
public class RestConversor {

    @Autowired
    private DtoToEntityImpl dtoToEntity;

    @Autowired
    private EntityToDtoImpl entityToDto;

    //DTO TO ENTITY-----------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/usuario/dto-entity", method = RequestMethod.POST)
    public Usuario usuarioDtoToEntity(@RequestBody UsuarioDTO usuarioDTO){
        return dtoToEntity.usuarioDtoToEntity(usuarioDTO);

    }

    @RequestMapping(value = "/perfil/dto-entity",method = RequestMethod.POST)
    public Perfil perfilDtoToEntity(@RequestBody PerfilDTO perfilDTO){
        return dtoToEntity.perfilDtoToPerfil(perfilDTO);
    }

    @RequestMapping(value = "/apartamento/dto-entity",method = RequestMethod.POST)
    public Apartamento apartamentoDtoToEntity(@RequestBody ApartamentoDTO apartamentoDTO) {
        return dtoToEntity.apartamentoDtoToEntity(apartamentoDTO);
    }

    @RequestMapping(value = "/reserva/dto-entity",method = RequestMethod.POST)
    public Reserva reservaDtoToEntity(@RequestBody ReservaDTO reservaDTO){
        return dtoToEntity.reservaDtoToEntity(reservaDTO);
    }

    @RequestMapping(value = "/capacidad/dto-entity",method = RequestMethod.POST)
    public Capacidad capacidadDtoToEntity(@RequestBody CapacidadDTO capacidadDTO){
        return dtoToEntity.capacidadDtoToEntity(capacidadDTO);
    }

    @RequestMapping(value = "/propietario/dto-entity",method = RequestMethod.POST)
    public Propietario propietarioDtoToEntity(@RequestBody PropietarioDTO propietarioDTO) {
        return dtoToEntity.propietarioDtoToEntity(propietarioDTO);
    }

    @RequestMapping(value = "/huesped/dto-entity",method = RequestMethod.POST)
    public Huesped huespedDtoToEntity(@RequestBody HuespedDTO huespedDTO) {
        return dtoToEntity.huespedDtoToEntity(huespedDTO);
    }

    //ENTITY TO DTO-----------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/usuario/entity-dto", method = RequestMethod.POST)
    public UsuarioDTO usuarioEntityToDto(@RequestBody Usuario usuario){
        return entityToDto.usuarioEntityToDto(usuario);
    }

    @RequestMapping(value = "/perfil/entity-dto",method = RequestMethod.POST)
    public PerfilDTO perfilEntityToDto(@RequestBody Perfil perfil){
        return entityToDto.perfilEntityToDto(perfil);
    }

    @RequestMapping(value = "/apartamento/entity-dto",method = RequestMethod.POST)
    public ApartamentoDTO apartamentoEntityToDto(@RequestBody Apartamento apartamento){
        return entityToDto.apartamentoEntityToDto(apartamento);
    }

    @RequestMapping(value = "/reserva/entity-dto",method = RequestMethod.POST)
    public ReservaDTO reservaEntityToDto(@RequestBody Reserva reserva) {
        return entityToDto.reservaEntityToDto(reserva);
    }

    @RequestMapping(value = "/capacidad/entity-dto",method = RequestMethod.POST)
    public CapacidadDTO capacidadEntityToDto(@RequestBody Capacidad capacidad) {
        return entityToDto.capacidadEntityToDto(capacidad);
    }

    @RequestMapping(value = "/propietario/entity-dto",method = RequestMethod.POST)
    public PropietarioDTO propietarioEntityToDton(@RequestBody Propietario propietario) {
        return entityToDto.propietarioEntityToDton(propietario);
    }

    @RequestMapping(value = "/huesped/entity-dto",method = RequestMethod.POST)
    public HuespedDTO huespedEntityToDto(@RequestBody Huesped huesped) {
        return entityToDto.huespedEntityToDto(huesped);
    }

}
