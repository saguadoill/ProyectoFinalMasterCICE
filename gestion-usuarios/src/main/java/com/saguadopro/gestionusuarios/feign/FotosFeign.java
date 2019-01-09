package com.saguadopro.gestionusuarios.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Repositorio Feign para la gestion de Fotografias/imagenes.
 * @see "FotosMsApplication"
 */
@Repository
@FeignClient("fotos-ms")
public interface FotosFeign {

    @RequestMapping(value = "/fotos/codificar", method = RequestMethod.POST)
    String codificarFoto(@RequestBody String foto_url);

    @RequestMapping(value = "/fotos/decodificar", method = RequestMethod.POST)
    byte[] decodificarFoto(@RequestBody String foto);

    @RequestMapping(value = "/fotos/guardar/{username}", method = RequestMethod.POST)
    String guardarFoto(@PathVariable(name = "username") String username, @RequestBody byte[] fotoParaGuardar,@RequestParam String origen);

}
