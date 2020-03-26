package com.example.hibernate.security;


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
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	//authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	//authorization
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //disable cross-site request forgery
			.authorizeRequests()
			    .antMatchers("/admin/**").hasRole("ADMIN")
			    .antMatchers("/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/h2-console/**", "/login").permitAll()
				//.anyRequest().authenticated()
			.and()
			.formLogin();
		
		//remove for production environment   
        http.headers().frameOptions().disable(); //to make h2 frame visible
    }
		
}
