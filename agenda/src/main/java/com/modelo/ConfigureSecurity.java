package com.modelo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ConfigureSecurity extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("emerson")
		.password("123")
		.roles("ADMIN")
		.and()	
		.withUser("wilton")
		.password("123")
		.roles("USER")
		.and().withUser("joyce")
		.password("123")				
		.roles("USER");
			
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()		
		
		.antMatchers("/resources**", "/webjars/**").permitAll()
	
		.antMatchers("/cadastro").hasAnyRole("ADMIN", "USER")
		.antMatchers("/editar/id").hasRole("ADMIN")
		.antMatchers("/delete/id").hasRole("ADMIN")			
		.anyRequest().authenticated()
		.and()
		.formLogin();
	}
}