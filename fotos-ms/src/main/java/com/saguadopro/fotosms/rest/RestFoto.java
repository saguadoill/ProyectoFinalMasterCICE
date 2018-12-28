package com.saguadopro.fotosms.rest;

import com.saguadopro.fotosms.services.impl.FotoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Clase Controladora de los ednpoints del microservicio para gestionar las imafenes/fotos de usuarios y apartamentos
 * @see {@link com.saguadopro.fotosms.services.FotoService}
 */
@RestController
public class RestFoto {

    @Autowired
    FotoImpl fotos;

    @RequestMapping(value = "/fotos/codificar", method = RequestMethod.POST)
    public String codificarFoto(@RequestBody String foto_url) {
        return fotos.codificarFoto(foto_url);
    }

    @RequestMapping(value = "/fotos/decodificar", method = RequestMethod.POST)
    public @ResponseBody byte[] decodificarFoto(@RequestBody String fotoBase64) {
        return fotos.decodificarFoto(fotoBase64);
    }

    @RequestMapping(value = "/fotos/guardar/{username}", method = RequestMethod.POST)
    public String guardarFoto(@PathVariable(name = "username") String username, @RequestBody byte[] fotoBytes, @RequestParam String origen) {
        return fotos.guardarFoto(username, fotoBytes, origen);
    }

}
