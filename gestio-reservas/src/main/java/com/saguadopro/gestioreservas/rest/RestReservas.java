package com.saguadopro.gestioreservas.rest;

import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
import com.saguadopro.gestioreservas.service.impl.ReservasImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase Controladora de los endpoints de la gestion de reservas
 * @see {@link com.saguadopro.gestioreservas.service.ReservasService}
 */
@RestController
public class RestReservas {

    @Autowired
    private ReservasImpl reservas;

    @RequestMapping(value = "/reservas",method = RequestMethod.GET)
    public List<ReservaDTO> listaReservas(){
        return reservas.listaReservas();
    }

    @RequestMapping(value = "/reservas/{idReserva}", method = RequestMethod.GET)
    public  List<ReservaDTO> buscarReserva(@PathVariable(value = "idReserva") String idReserva){
        return reservas.buscarReserva(idReserva);
    }

    @RequestMapping(value = "/reservas/{idReserva}", method = RequestMethod.DELETE)
    public Boolean eliminarReserva(@PathVariable(value = "idReserva") String idReserva){
        return reservas.eliminarReserva(idReserva);
    }

    @RequestMapping(value = "/reservas", method = RequestMethod.PUT)
    public Boolean modificarReserva(@RequestBody ReservaDTO reservaDTO){
        return reservas.modificarReserva(reservaDTO);
    }

    @RequestMapping(value = "/reservas/booking", method = RequestMethod.POST)
    public void getBookinReservas(@RequestBody String xmlFile){
        reservas.getBookinReservas(xmlFile);
    }
}
