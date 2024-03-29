package com.example.back_to_flutter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.back_to_flutter.security.filter.AuthenticationFilter;
import com.example.back_to_flutter.security.filter.ExaptionHandler;
import com.example.back_to_flutter.security.filter.JWTAuthorizationFilter;
import com.example.back_to_flutter.security.manager.CustomAuthenticationManager;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	CustomAuthenticationManager manager;
    @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		// AuthenticationFilter authenticationFilter = new AuthenticationFilter(manager);
		// authenticationFilter.setFilterProcessesUrl("/authenticate");
		http
				.csrf((csrf) -> csrf
					.disable())
				.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers(HttpMethod.GET,"/").permitAll()
				.requestMatchers(HttpMethod.POST, "/signup").permitAll()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated())
				.httpBasic(withDefaults())
				.formLogin(withDefaults())
				.addFilterBefore(new ExaptionHandler(), AuthenticationFilter.class)
				.addFilter(new AuthenticationFilter(manager))
				.addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
				.sessionManagement((sessionManagement) -> sessionManagement
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

    @Bean
	 InMemoryUserDetailsManager userDetailsService() {
      
		UserDetails user = User.builder()
		.username("user")
		.password(encoder.encode("password"))
		.build();
		return new InMemoryUserDetailsManager(user);
	}
	
}
