package com.example.eduForge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.eduForge.security.JwtAuthenticationEntryPoint;
import com.example.eduForge.security.JwtAuthenticationFilter;
import com.example.eduForge.service.CustomUserDetailService;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
//@EnableWebMvc
@Configuration
public class SecurityConfig {
	 	@Autowired
	    private JwtAuthenticationEntryPoint point;
	    @Autowired
	    private JwtAuthenticationFilter filter;
	    @Autowired
	    private CustomUserDetailService userDetailService;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	http.csrf(csrf->csrf.disable())
	    	.cors(cors->cors.disable())
	    	.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
	                .requestMatchers(
//	                		new AntPathRequestMatcher("/home/hello")
//	                        ,
	                        new AntPathRequestMatcher("/auth/**")).permitAll()
//	                        .requestMatchers("/api/hello", "/api/authenticate", "/api/signup").permitAll()
	                .anyRequest().authenticated())
	    	.exceptionHandling(ex->ex.authenticationEntryPoint(point))
	    	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	      return http.build();
//	      http.csrf(csrf->csrf.disable())
//	      .cors(cors->cors.disable())
//	      .authorizeHttpRequests(
//	    		  auth->
//	    		  auth
//	    		  .requestMatchers("/home/**").authenticated()
//	    		  
//	    		  .anyRequest().authenticated())
//	      .exceptionHandling(ex->ex.authenticationEntryPoint(point))
//	      .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//	      http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//	      return http.build();
	    }
	    @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider()
	    {
	    	DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
	    	provider.setUserDetailsService(userDetailService);
	    	provider.setPasswordEncoder(passwordEncoder);
	    	return provider;
	    }
}
