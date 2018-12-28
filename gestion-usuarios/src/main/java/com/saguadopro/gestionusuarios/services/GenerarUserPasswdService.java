package com.saguadopro.gestionusuarios.services;


import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Servicio encargado de la generacion del nombre de usuario y password
 */
@Service
public class GenerarUserPasswdService {

    @Autowired
    private UsuariosService gestionUsuariosService;

    /**
     * Metodo que genera el nombre de usuario a partir del Nombre y Apellidos
     * @param nombre    - Nombre del usuario
     * @param apellidos - Apellidso del usuario
     * @return String con el username
     */
    public String generarUsername(String nombre, String apellidos) {
        nombre = StringUtils.stripAccents(nombre).trim();
        apellidos = StringUtils.stripAccents(apellidos).trim();
        while (apellidos.length() < 6) apellidos += "0";
        String usernameGenerado = (nombre.substring(0, 1) + apellidos.substring(0, 6).trim()).toLowerCase();
        int count = 0;
        while (existeUsuario(usernameGenerado)) {
            count++;
            usernameGenerado += count;
        }
        return usernameGenerado;
    }

    /**
     * Metodo para generar un possword a aprtir del username del usuario
     * @param username - Nombre de usuario
     * @return String con el password
     */
    public String generarPasswd(String username) {
        StringBuilder passwdAutogenerado = new StringBuilder(username.replace((username.charAt(0)), (username.toUpperCase().charAt(0))));
        LocalTime tiempo = LocalTime.now();
        StringBuilder horaSb = new StringBuilder();
        horaSb.append(tiempo.getHour()).append(tiempo.getMinute()).append(tiempo.getSecond());
        passwdAutogenerado.append(horaSb);
        return passwdAutogenerado.toString();
    }

    /**
     * Metodo para comprobar si un username ya existe
     * @param username - Nombre de usuario
     * @return True; si el username ya existe. False; si no existe
     */
    private Boolean existeUsuario(String username) {
        List<UsuarioDTO> usuariosExistentes = gestionUsuariosService.buscarUsuario(username);
        if (!usuariosExistentes.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
