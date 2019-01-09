package com.saguadopro.clietenweb.configs;

import com.saguadopro.clietenweb.services.UserDetailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase encargada de la configuración de la seguridad de la aplicación
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = Logger.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserDetailServiceImpl userDetailService;

    /**
     * Método para encriptar el password del usuario
     *
     * @return - BCryptPasswordEncoder, password encriptado
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Método para buscar al usuario en la BBDD y setear el password
     *
     * @param auth - AuthenticationManagerBuilder
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            logger.fatal("No se ha podido recuperar el usuario de la BBDD: " + e.getMessage());
        }
    }

    /**
     * Método para la configuración del acceso
     *
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/login", "/logout", "/403page").permitAll();

        http.authorizeRequests().antMatchers("/home", "/").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_CLEAN')");

        http.authorizeRequests().antMatchers("/apartamentos/*", "/reservas/*", "/parkins/*", "/servicios/*").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/limpieza/*").access("hasAnyRole('ROLE_CLEAN','ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/*", "/usuarios/*").access("hasAnyRole('ROLE_ADMIN')");

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
