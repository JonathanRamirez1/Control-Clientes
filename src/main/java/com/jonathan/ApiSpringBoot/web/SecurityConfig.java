package com.jonathan.ApiSpringBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Restriccion de URL*
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/edit/**", "/add/**", "/delete") //Restriccion de los paths, los * significa que todos los demas paths siguientes al que se especifico seran restringidos, eje: http://localhost:8080/edit/hola
                .hasRole("ADMIN") //solo el usuraio ADMIN puede acceder a estos paths ("/edit/", "/add/", "/delete")
                .antMatchers("/") //path raiz, el primer path
                .hasAnyRole("USER", "ADMIN") //Cualquier usuario puede acceder al path raiz
                .and()
                .formLogin()
                .loginPage("/login") //Pone esta pagina por default
                .and()
                .exceptionHandling().accessDeniedPage("/errors/403") //Sino se tiene acceso manda esta pagina
                ;
    }
}

/**
 * Se agrega usuario*
 */
/* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("{noop}123")
                .roles("USER")
                ;
    }*/
