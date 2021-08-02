package com.eprostam.first.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eprostam.first.app.ws.services.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserService userDetailService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public WebSecurity(UserService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailService = userDetailService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("configure HttpSecurity => " + httpSecurity);
		
		httpSecurity
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGNUP_URL).permitAll()
		//.antMatchers(HttpMethod.GET, "/users").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
	    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	    filter.setFilterProcessesUrl("/users/login");
	    return filter;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure AuthenticationManagerBuilder => " + auth);
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	
	

}
