package com.saguadopro.gestioreservas.rest;

import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
import com.saguadopro.gestioreservas.service.impl.ReservasImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class RestReservas {

    @Autowired
    ReservasImpl reservas;

    @RequestMapping(value = "/reservas",method = RequestMethod.GET)
    public List<ReservaDTO> listaReservas(){
        return reservas.listaReservas();
    }

    @RequestMapping(value = "/reservas/{idReserva}", method = RequestMethod.GET)
    public ReservaDTO buscarReserva(@PathVariable(value = "idReserva") String idReserva){
        return reservas.buscarReserva(idReserva);
    }

    @RequestMapping(value = "/reservas/booking", method = RequestMethod.POST)
    public void getBookinReservas(@RequestBody String xmlFile){
        reservas.getBookinReservas(xmlFile);
    }
}
