package com.saguadopro.clietenweb.configs;

import com.saguadopro.clietenweb.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        // Servicio para encontrar al usuario en la base de datos y setear el password
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //Las siguientes paginas no requieren login.
        http.authorizeRequests().antMatchers("/login","/logout","/prueba").permitAll();

        // la web userInfoPage requiere role de admin y usuario. Si no se loga, se redireccionara a /login
        http.authorizeRequests().antMatchers("/home","/*","/index","/crearUsuario").access("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_CLEAN')");

        //Paginas accesibles para el usuario con perfil de Administrador
        http.authorizeRequests().antMatchers("/*").access("hasAnyRole('ROLE_ADMIN')");

        // pagina de "acceso denengado" para los usuarios que quieren acceder a páginas a las que no tienen acceso
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Configuracion para el formulario de Login
        http.authorizeRequests().and().formLogin()//
                // Configuracion paguina login.
                .loginProcessingUrl("/j_spring_security_check") // URL del action del formulario
                .loginPage("/login")//
                .defaultSuccessUrl("/home")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Configuracion pagina logout
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true);

        // Control de sesión ---------------------------
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(1)
                .expiredUrl("/login");
    }

}
