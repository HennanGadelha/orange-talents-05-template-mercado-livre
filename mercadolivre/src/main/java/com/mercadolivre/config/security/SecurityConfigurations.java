package com.mercadolivre.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mercadolivre.usuario.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {


    @Autowired
    private Autenticacao autenticacao;
    
    @Autowired 
    TokenUsuario tokenUsuario;
    
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //config autorizaçao
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
        		.antMatchers(HttpMethod.POST, "/cadastro").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/auth").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/produtos/**").authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthFilter(tokenUsuario, usuarioRepository), 
                		UsernamePasswordAuthenticationFilter.class);

    }


    // config autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(autenticacao).passwordEncoder(bpasswordEncoder());
    }


    @Bean
    public BCryptPasswordEncoder bpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
