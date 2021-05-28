package com.mercadolivre.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mercadolivre.usuario.Usuario;
import com.mercadolivre.usuario.UsuarioRepository;

public class AuthFilter extends OncePerRequestFilter  {

	private TokenUsuario tokenUsuario;
	private UsuarioRepository usuarioRepository;
	
	public AuthFilter(TokenUsuario tokenUsuario,UsuarioRepository usuarioRepository ) {
		this.tokenUsuario = tokenUsuario;
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = capturaToken(request);
		
		boolean isValid = tokenUsuario.isTokenValido(token);
		
		if(isValid) autenticarUsuario(token);
		
		filterChain.doFilter(request, response);
	}


	private void autenticarUsuario(String token) {
		// TODO Auto-generated method stub
		
		Long id = tokenUsuario.getUsuarioToken(token);
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isEmpty()) throw new AccessDeniedException("Token inválido");
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}


	private String capturaToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		
		boolean tokenInvalido = (token == null || token.isEmpty() || !token.startsWith("Bearer "));
		if (tokenInvalido) {
			return null;
		}

		return token.substring(7, token.length());
		
	}

}
