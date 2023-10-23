package com.tavsanci.springsecurity.demo.config;

import org.apache.taglibs.standard.lang.jstl.AndOperator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Add our users for in a memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication().withUser(users.username("Ozenc").password("Ozenc123").roles("EMPLOYEE","BIG_BOSS","ADMIN"))
									 .withUser(users.username("Jane").password("Test123").roles("EMPLOYEE","ADMIN"));
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")  //.anyRequest().authenticated()
				.antMatchers("/leaders/**").hasRole("BIG_BOSS")
				.antMatchers("/systems/**").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginPage("/showMyLoginPage") // Show our custom form at the request mapping
				.loginProcessingUrl("/authenticateTheUser") // check user id and password
				.permitAll()  // Allow anyone to see login page 
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}

	
}
