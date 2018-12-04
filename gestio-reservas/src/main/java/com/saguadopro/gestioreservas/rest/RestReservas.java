package com.saguadopro.gestioreservas.rest;

import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
import com.saguadopro.gestioreservas.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class RestReservas {

    @Autowired
    ReservasService reservasService;

    @RequestMapping(value = "/reservas",method = RequestMethod.GET)
    public List<ReservaDTO> listaReservas(){
        return reservasService.listaReservas();
    }

    @RequestMapping(value = "/reservas/{idReserva}")
    public ReservaDTO buscarReserva(@PathParam(value = "idReserva") Long idReserva){
        return reservasService.buscarReserva(idReserva);
    }

    @RequestMapping(value = "/reservas/booking", method = RequestMethod.POST)
    public void getBookinReservas(@RequestBody String xmlFile){
        reservasService.getBookinReservas(xmlFile);
    }
}
