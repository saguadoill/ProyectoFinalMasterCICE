package com.saguadopro.clietenweb.feign;


import com.saguadopro.clietenweb.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@FeignClient("gestion-usuarios")
public interface UsuariosFeign {

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    HttpStatus crearUsuarioFeign(UsuarioDTO usuarioDTO);

    @RequestMapping(value = "/usuarios/{username_id}",method = RequestMethod.GET)
    List<UsuarioDTO> buscarUsuario(@PathVariable(value = "username_id") String username_id);

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    List<UsuarioDTO> listarUsuarios();

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    Boolean eliminarUsuario(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/usuarios",method = RequestMethod.PUT)
    HttpStatus modificarUsuario(UsuarioDTO usuarioModificadoDto);

    @RequestMapping(value = "/usuarios/{id}",method = RequestMethod.PUT)
    HttpStatus cambiarPasswd(@PathVariable(value = "id") Long id,@RequestBody String passwd);

    @RequestMapping(value = "/usuarios/campos")
    List<String> generarUserPass(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "apellidos") String apellidos);
}
