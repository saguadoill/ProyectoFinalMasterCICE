package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

/**
 * Clase que devuelve el nombre de usuario y el GrantedAuthority
 */
public class WebUtils {

    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public static Boolean validarFormatoPasswd(String passwd){
        boolean mayuscula = false;
        boolean numero = false;
        for (Character c: passwd.toCharArray()) {
            if (Character.isAlphabetic(c)){
                mayuscula = true;
            }else {
                numero = true;
            }
        }
        if (mayuscula & numero){
            return true;
        }else {
            return false;
        }
    }

    public static ResponseEntity verificarPasswd(Map<String,String> campos, UsuarioDTO usuarioDTO, Principal principal, UsuariosFeign usuariosFeign){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String passActual = campos.get("passwdActual");
        String nueva = campos.get("passwdNueva");
        String repetida = campos.get("passwdRepetida");
        String passActualEnc = usuarioDTO.getPasswd();

        if (passwordEncoder.matches(passActual, passActualEnc)) {
            if (!passwordEncoder.matches(nueva, passActualEnc)) {
                if (repetida.equals(nueva)) {
                    if (WebUtils.validarFormatoPasswd(nueva)) {
                        Long idUsuario = usuariosFeign.buscarUsuario(principal.getName()).get(0).getIdUsuario();
                        System.out.println("id: " + idUsuario + " Passwd: " + passwordEncoder.encode(nueva));
                        return new ResponseEntity(usuariosFeign.cambiarPasswd(idUsuario, passwordEncoder.encode(nueva)));
                    } else {
                        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
                    }
                } else {
                    System.out.println("NO SON IGUALES OSTIAS");
                    return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
                }
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
