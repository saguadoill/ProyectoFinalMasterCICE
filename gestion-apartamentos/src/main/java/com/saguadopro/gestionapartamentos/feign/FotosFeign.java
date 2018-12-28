package com.saguadopro.gestionapartamentos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient("fotos-ms")
public interface FotosFeign {

    @RequestMapping(value = "/fotos/codificar")
    String codificarFoto(@RequestBody String foto_url);

    @RequestMapping(value = "/fotos/decodificar")
    byte[] decodificarFoto(@RequestBody String foto);

    @RequestMapping(value = "/fotos/guardar/{username}")
    String guardarFoto(@PathVariable(name = "username") String username, @RequestBody byte[] fotoParaGuardar, @RequestParam String origen);

}
