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
 * Clase que devuelve el nombre de usuario , valida contraseñas y devuelve el GrantedAuthority para SpringSecurity
 */
public class WebUtils {

    /**
     * Método que devuelve el GarantedAuthority para el control de acceso
     *
     * @param user - Usuario
     * @return - Representacion en String del GrantedAuthority
     */
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

    /**
     * Método que comprueba que el password cumple ciertas reglas.
     * - Tiene que contener mayusculas y minusculas
     * - Tiene que contener al menos un numero.
     *
     * @param passwd - Contraseña a validar
     * @return - True; si cumple los requisitos. Flase; si no los cumple.
     */
    public static Boolean validarFormatoPasswd(String passwd) {
        boolean mayuscula = false;
        boolean numero = false;
        for (Character c : passwd.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                mayuscula = true;
            } else {
                numero = true;
            }
        }
        if (mayuscula & numero) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comprueba que la nueva contraseña no es igual a la actual y que se ha repetido de forma correcta
     *
     * @param campos        - Contraseñas introducidas
     * @param usuarioDTO    - Dto de tipo Usuario. El usuario que cambia su contraseña
     * @param principal     - Usuario logado
     * @param usuariosFeign - Conector con la BBDD de usuarios
     * @return - Respuesta con un estado Http.
     */
    public static ResponseEntity verificarPasswd(Map<String, String> campos, UsuarioDTO usuarioDTO, Principal principal, UsuariosFeign usuariosFeign) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String actual = campos.get("passwdActual");
        String nueva = campos.get("passwdNueva");
        String repetida = campos.get("passwdRepetida");
        String actualEncriptada = usuarioDTO.getPasswd();

        if (passwordEncoder.matches(actual, actualEncriptada)) {
            if (!passwordEncoder.matches(nueva, actualEncriptada)) {
                if (repetida.equals(nueva)) {
                    if (WebUtils.validarFormatoPasswd(nueva)) {
                        Long idUsuario = usuariosFeign.buscarUsuario(principal.getName()).get(0).getIdUsuario();
                        return new ResponseEntity(usuariosFeign.cambiarPasswd(idUsuario, passwordEncoder.encode(nueva)));
                    } else {
                        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
                    }
                } else {
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
