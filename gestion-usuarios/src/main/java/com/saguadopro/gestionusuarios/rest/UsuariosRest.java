package com.saguadopro.gestionusuarios.rest;

import com.saguadopro.gestionusuarios.rest.dto.PerfilDTO;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import com.saguadopro.gestionusuarios.services.impl.UsuariosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuariosRest {

    @Autowired
    UsuariosImp usuarios;

    @RequestMapping(value = "/usuarios",method = RequestMethod.POST)
    public HttpStatus crearUsuario(@RequestBody UsuarioDTO usuarioDTO){   // para probar con el postman @RequestBody
        if (usuarios.crearUsuario(usuarioDTO)){
            return HttpStatus.CREATED;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/usuarios/{idUsuario}",method = RequestMethod.DELETE)
    public Boolean eliminarUsuario(@PathVariable(value = "idUsuario") Long idUsuario){
        if (usuarios.eliminarUsuario(idUsuario)){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/usuarios",method = RequestMethod.PUT)
    public HttpStatus modificarUsuario(@RequestBody UsuarioDTO usuarioModificado){
        if (usuarios.modificarUsuario(usuarioModificado)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/usuarios/{id}",method = RequestMethod.PUT)
    public HttpStatus cambiarPasswd(@PathVariable(value = "id") Long id ,@RequestBody String passwd){
        if (usuarios.cambiarPasswd(id,passwd)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/usuarios/{username}", method = RequestMethod.GET)
    public List<UsuarioDTO> buscarUsuario(@PathVariable(value = "username") String username){

        return usuarios.buscarUsuario(username);
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<UsuarioDTO> listarUsuarios(){
        return usuarios.listarUsuarios();
    }

    @RequestMapping(value = "/usuarios/perfiles", method = RequestMethod.GET)
    public List<PerfilDTO> listaPerfiles(){
        return usuarios.listaPerfiles();
    }

    @RequestMapping(value = "/usuarios/campos", method = RequestMethod.GET)
    public List<String> generarCampos(@RequestParam(value="nombre")String nombre, @RequestParam(value = "apellidos") String apellidos ){
        return usuarios.generarCampos(nombre,apellidos);
    }


}
