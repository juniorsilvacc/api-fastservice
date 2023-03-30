package com.juniorsilvacc.fastservice.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.juniorsilvacc.fastservice.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{
	
	@Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token;

        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer ", "");
            var subject = this.jwtToken.getSubject(token);

            var user = this.repository.findByUserEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
		
	}

}
