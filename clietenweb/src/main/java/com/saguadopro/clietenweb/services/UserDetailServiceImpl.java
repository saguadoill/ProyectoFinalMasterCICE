package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuariosFeign usuariosFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Obtenemos un objeto usuario de la base de datos
        List<UsuarioDTO> appUser = usuariosFeign.buscarUsuario(username);
        System.out.println("Found User: " + appUser.get(0));

        //Obtenemos un a lista de los roles que tiene el usuario
        List<String> roleNames = new ArrayList<>();
        roleNames.add(appUser.get(0).getPerfil().getNombrePerfil());
        //Añadir los roles de la base de datos
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(roleNames.get(0));
            grantList.add(authority);
        }

        UserDetails userDetails = new User(appUser.get(0).getUsername(),appUser.get(0).getPasswd(),grantList);

        return userDetails;
    }
}
