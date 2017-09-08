package com.umanteam.dadakar.admin.front.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//				.antMatchers("/", "/index", "/css/**", "/js/**", "/font/**").permitAll()
				.antMatchers("/user/**", "/template/**", "/price/**", "/plainte/**").hasAnyRole("ADMIN", "SUPERUSER")
				.antMatchers("/admin/**").hasAnyRole("SUPERUSER")
				.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.permitAll()
			.and().logout()
				.permitAll();
//			.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
				
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("password").roles("ADMIN")
			.and()
			.withUser("super").password("password").roles("SUPERUSER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/font/**");
	}
}
