package com.saguadopro.fotosms.rest;

import com.saguadopro.fotosms.services.impl.FotoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@RestController
public class RestFoto {

    @Autowired
    FotoImpl fotos;

    @RequestMapping(value = "/fotos/codificar")
    public String codificarFoto(@RequestBody String foto_url) {
        return fotos.codificarFoto(foto_url);
    }

    @RequestMapping(value = "/fotos/decodificar")
    public @ResponseBody byte[] decodificarFoto(@RequestBody String foto) {
        System.out.println(foto);
        return fotos.decodificarFoto(foto);
}

    @RequestMapping(value = "/fotos/guardar/{username}")
    public String guardarFoto(@PathVariable(name = "username") String username,@RequestBody byte[] fotoByte) {
       return fotos.guardarFoto(username,fotoByte);
    }

    @RequestMapping(value = "/fotos/resize")
    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        return fotos.resize(img,newW,newH);
    }

}
