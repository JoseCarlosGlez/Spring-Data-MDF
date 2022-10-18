package com.mdf.springjpa.Spring.jpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class ProjectSecurityConfig {
	
	@Value("${URLS.Authenticated}")
	private String authenticatedURL;
	
	@Value("${URLS.Permitall}")
	private String permitedURL;
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		
		String[] listUrlAuthenticated = this.authenticatedURL.split(",");	
		String[] listUrlPermited = this.permitedURL.split(",");
		http.
		csrf().disable()
		.authorizeRequests()
		.antMatchers(listUrlAuthenticated).authenticated()
		.antMatchers(listUrlPermited).permitAll()
		.and().formLogin()
		.and().httpBasic();		
		
		
		return http.build();
	}
	/**
	 *  NoOpPasswordEncoder no es seguro
	 * @return
	 */
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Hibernate5Module hibernate5Module() {
	    return new Hibernate5Module();
	}

}
