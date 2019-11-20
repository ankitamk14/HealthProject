package com.health.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.health.service.impl.UserSecurityService;
import com.health.utility.SecurityUtility;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	@Autowired
	private UserSecurityService userSecurityService;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}
	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/image/**",
			"/",
			"/newUser",
			"/forgetPassword",
			"/login",
			"/s/**",
			"/adminDeatail/**",
			"/fonts/**"	
	};
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").hasAnyAuthority()
				/* .antMatchers("/adminDeatail/**").hasRole("Admin") */	
				/* .antMatchers("/userDetail/**").hasAnyAuthority("Admin") */
			.antMatchers(PUBLIC_MATCHERS).
				permitAll().anyRequest().authenticated().
				and()
				.exceptionHandling().accessDeniedPage("/access-denied");;
		
		/*
		 * http
		 * 
		 * .authorizeRequests() .antMatchers("/userDetail/**").hasRole("ROLE_USER")
		 * .antMatchers("/adminDeatail/**").access("ROLE_ADMIN").anyRequest().
		 * authenticated();
		 */ 
		
		  http.
		  formLogin().
		  defaultSuccessUrl("/adminDeatail", true);
		 
		
		
		 
		http
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/login?error")
			//("userDetail.html")
			/*.defaultSuccessUrl("/")*/
			.loginPage("/login").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe();
	}
	
	
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
		
	}

}
