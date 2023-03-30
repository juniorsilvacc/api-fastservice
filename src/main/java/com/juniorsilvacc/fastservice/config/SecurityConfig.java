package com.juniorsilvacc.fastservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.juniorsilvacc.fastservice.security.FilterToken;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private FilterToken filter;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 return http
				 .csrf().disable()
				 .sessionManagement(
		            		session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				 .authorizeHttpRequests(
							 authorizeHttpRequests -> authorizeHttpRequests
							 .requestMatchers(
									"/auth/signin",
									"/auth/refresh/**",
			                    	"/swagger-ui/**",
			                    	"/v3/api-docs/**"
			                 ).permitAll()
						.requestMatchers("/api/**").authenticated()
		                .requestMatchers("/users").denyAll()
				)
				.cors()
				.and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
				 
	                
    }
	
	@Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
