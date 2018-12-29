package com.saguadopro.gestionapartamentos.feign;


import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.entities.Capacidad;
import com.saguadopro.gestionapartamentos.entities.Huesped;
import com.saguadopro.gestionapartamentos.entities.Propietario;
import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;
import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Repositorio Feign para la conversion de Entidades a Dto y viceversa
 * @see "ConversorMicroservice"
 */
@Repository
@FeignClient("conversor-ms")
public interface ConversorFeign {

    //DTO to ENTIY
    @RequestMapping(value = "/conversor/apartamento/dto-entity", method = RequestMethod.POST)
    Apartamento apartamentoDtoToEntity(@RequestBody ApartamentoDTO apartamentoDTO);

    @RequestMapping(value = "/conversor/capacidad/dto-entity",method = RequestMethod.POST)
    Capacidad capacidadDtoToEntity(@RequestBody CapacidadDTO capacidadDTO);

    @RequestMapping(value = "/conversor/propietario/dto-entity",method = RequestMethod.POST)
    Propietario propietarioDtoToEntity(@RequestBody PropietarioDTO propietarioDTO);

    @RequestMapping(value = "/conversor/huesped/dto-entity",method = RequestMethod.POST)
    Huesped huespedDtoToEntity(@RequestBody HuespedDTO huespedDTO);

    //ENTITY TO DTO
    @RequestMapping(value = "/conversor/apartamento/entity-dto", method = RequestMethod.POST)
    ApartamentoDTO apartamentoEntityToDto(Apartamento apartamento);

    @RequestMapping(value = "/conversor/capacidad/entity-dto",method = RequestMethod.POST)
    CapacidadDTO capacidadEntityToDto(@RequestBody Capacidad capacidad);

    @RequestMapping(value = "/conversor/propietario/entity-dto",method = RequestMethod.POST)
    PropietarioDTO propietarioEntityToDton(@RequestBody Propietario propietario);

    @RequestMapping(value = "/conversor/huesped/entity-dto",method = RequestMethod.POST)
    HuespedDTO huespedEntityToDto(@RequestBody Huesped huesped);
}
