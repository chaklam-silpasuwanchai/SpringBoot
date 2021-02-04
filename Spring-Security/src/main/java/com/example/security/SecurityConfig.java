package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration // specifies that this configuration
				// to be applied at runtime
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authentication
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //allows h2 to works
			.authorizeRequests()
				.antMatchers("/h2-console/**", "/login", "/register").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").hasAnyRole("ADMIN", "USER")
			.and()
			.formLogin()
				.loginPage("/login").permitAll() //use your own login/logout page
				.defaultSuccessUrl("/home", true)
			.and()
			.logout().invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logout-success").permitAll();
		
		//to make h2 frame visible
		http.headers().frameOptions().disable();
		
	}
	
}
