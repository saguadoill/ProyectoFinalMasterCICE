package com.saguadopro.clietenweb.feign;


import com.saguadopro.clietenweb.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@FeignClient("gestion-reservas")
public interface ReservasFeign {

    @RequestMapping(value = "/reservas",method = RequestMethod.GET)
    List<ReservaDTO> listaReservas();

    @RequestMapping(value = "/reservas/{idReserva}", method = RequestMethod.GET)
    List<ReservaDTO> buscarReserva(@PathVariable(value = "idReserva") String idReserva);

    @RequestMapping(value = "/reservas", method = RequestMethod.PUT)
    Boolean modificarReserva(@RequestBody ReservaDTO reservaDTO);

    @RequestMapping(value = "/reservas/{idReserva}", method = RequestMethod.DELETE)
    ReservaDTO eliminarReserva(@PathVariable(value = "idReserva") String idReserva);
}
