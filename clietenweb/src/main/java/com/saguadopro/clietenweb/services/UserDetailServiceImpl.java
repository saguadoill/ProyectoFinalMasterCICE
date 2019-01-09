package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para cargar los datos de usuario a Spring Security
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static Logger log = Logger.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UsuariosFeign usuariosFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            //Obtenemos un objeto usuario de la base de datos
            List<UsuarioDTO> appUser = usuariosFeign.buscarUsuario(username);

            //Obtenemos un a lista de los roles que tiene el usuario
            List<String> roleNames = new ArrayList<>();
            roleNames.add(appUser.get(0).getPerfil().getNombrePerfil());

            //Añadir los roles a SpringSecurity
            List<GrantedAuthority> grantList = new ArrayList<>();
            if (roleNames != null) {
                GrantedAuthority authority = new SimpleGrantedAuthority(roleNames.get(0));
                grantList.add(authority);
            }
            userDetails = new User(appUser.get(0).getUsername(), appUser.get(0).getPasswd(), grantList);
        }catch (InternalAuthenticationServiceException e){
            log.fatal("Error al acceder a la aplicación. Nombre de usuario y contraseña incorrectos o inexistentes");
        } catch (Exception e){
            log.fatal("Error inesperado de inicio de sesion: "+e.getMessage());
        }
        return userDetails;
    }
}
