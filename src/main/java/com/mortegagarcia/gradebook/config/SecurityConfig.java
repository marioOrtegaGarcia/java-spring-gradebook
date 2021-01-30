package com.mortegagarcia.gradebook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		User.UserBuilder userBuilder = User.builder();

		// Encrypted password = "1234
		auth.inMemoryAuthentication()
				.withUser(userBuilder.username("student").password("{bcrypt}$2y$12$fa8O2Z8ckCWHtoAoXvIjTeQCGAAXJ0rS49LSiHC3ZSp5CZB0Lrn6i").roles("STUDENT"))
				.withUser(userBuilder.username("professor").password("{bcrypt}$2y$12$fa8O2Z8ckCWHtoAoXvIjTeQCGAAXJ0rS49LSiHC3ZSp5CZB0Lrn6i").roles("PROFESSOR"))
				.withUser(userBuilder.username("admin").password("{bcrypt}$2y$12$fa8O2Z8ckCWHtoAoXvIjTeQCGAAXJ0rS49LSiHC3ZSp5CZB0Lrn6i").roles("ADMIN"));
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				// Swagger
//				.antMatchers("/swagger-resources/*", "*.html", "/api/v1/swagger.json").hasRole("ADMIN")
				.and()
				.httpBasic()
				.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
