package com.example.sec.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication().withUser("Sam")
//		.password(passwordEncoder().encode("aaaa"))
//		.roles("USER");
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	.antMatchers("/","/showRegistrationForm","/doRegister","/h2-console/**").permitAll()
	.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
	.antMatchers("/admin/**").hasRole("ADMIN")
	.anyRequest().authenticated()
	.and()
	.formLogin().permitAll()

	
	.and()
	.logout().logoutSuccessUrl("/").permitAll();
	
	
	http.headers().frameOptions().disable();
	
	
	http.csrf().disable();
	
//	http.anonymous().disable();
	
	}

	@Bean
	public AuthenticationManager customeAuthenticationManager() {
		try {
			return authenticationManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
