package com.saguadopro.gestionusuarios.services;


import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.repositories.GestionUsuariosRepo;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;


@Service
public class GenerarUserPasswdService {

    @Autowired
    private GestionUsuariosService gestionUsuariosService;

    public String generarUsername(String nombre, String apellidos) {
        nombre = StringUtils.stripAccents(nombre).trim();
        apellidos = StringUtils.stripAccents(apellidos).trim();
        while (apellidos.length()<6) apellidos += "0";
        String usernameGenerado = (nombre.substring(0,1)+apellidos.substring(0,6).trim()).toLowerCase();
        int count = 0;
        while (existeUsuario(usernameGenerado)){
            count++;
            usernameGenerado = (nombre.substring(0,1)+apellidos.substring(0,6).trim()).toLowerCase()+count;
        }
        return usernameGenerado;
    }

    public String generarPasswd(String username) {
        StringBuilder passwdAutogenerado = new StringBuilder(username.replace((username.charAt(0)),(username.toUpperCase().charAt(0))));
        LocalTime tiempo = LocalTime.now();
        StringBuilder horaSb  = new StringBuilder();
        horaSb.append(tiempo.getHour()).append(tiempo.getMinute()).append(tiempo.getSecond());
        passwdAutogenerado.append(horaSb);
        return passwdAutogenerado.toString();
    }


    private Boolean existeUsuario(String username){
        List<UsuarioDTO> usuariosExistentes = gestionUsuariosService.buscarUsuario(username);
        if(!usuariosExistentes.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

}
