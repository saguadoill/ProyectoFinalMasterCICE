package com.saguadopro.gestionusuarios.rest;

import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import com.saguadopro.gestionusuarios.services.GestionUsuariosService;
import com.saguadopro.gestionusuarios.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionUsuariosRest {

    @Autowired
    GestionUsuariosService gestionUsuariosService;

    @RequestMapping(value = "/usuarios",method = RequestMethod.POST)
    public HttpStatus crearUsuario(@RequestBody UsuarioDTO usuarioDTO){   // para probar con el postman @RequestBody
        if (gestionUsuariosService.crearUsuario(usuarioDTO)){
            return HttpStatus.CREATED;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/usuarios/{idUsuario}",method = RequestMethod.DELETE)
    public Boolean eliminarUsuario(@PathVariable(value = "idUsuario") Long idUsuario){
        if (gestionUsuariosService.eliminarUsuario(idUsuario)){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/usuarios",method = RequestMethod.PUT)
    public HttpStatus modificarUsuario(@RequestBody UsuarioDTO usuarioModificado){
        if (gestionUsuariosService.modificarUsuario(usuarioModificado)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/usuarios/{id}",method = RequestMethod.PUT)
    public HttpStatus cambiarPasswd(@PathVariable(value = "id") Long id ,@RequestBody String passwd){
        if (gestionUsuariosService.cambiarPasswd(id,passwd)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/usuarios/{username}", method = RequestMethod.GET)
    public List<UsuarioDTO> buscarUsuario(@PathVariable(value = "username") String username){

        return gestionUsuariosService.buscarUsuario(username);
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<UsuarioDTO> listarUsuarios(){
        return gestionUsuariosService.listarUsuarios();
    }

    @RequestMapping(value = "/campos", method = RequestMethod.GET)
    public List<String> generarCampos(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "apellidos") String apellidos){
        return gestionUsuariosService.generarCampos(nombre,apellidos);
    }
}
