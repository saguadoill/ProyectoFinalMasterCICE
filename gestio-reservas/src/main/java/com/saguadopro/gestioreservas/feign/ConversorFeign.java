package com.saguadopro.gestioreservas.feign;



import com.saguadopro.gestioreservas.entities.Reserva;
import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
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

    //DTO TO ENTIY
    @RequestMapping(value = "/conversor/reserva/dto-entity",method = RequestMethod.POST)
    Reserva reservaDtoToEntity(@RequestBody ReservaDTO reservaDTO);

    //ENTITY TO DTO
    @RequestMapping(value = "/conversor/reserva/entity-dto",method = RequestMethod.POST)
    ReservaDTO reservaEntityToDto(@RequestBody Reserva reserva);
}
