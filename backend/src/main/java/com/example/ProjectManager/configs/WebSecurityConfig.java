// package com.example.ProjectManager.configs;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// import jakarta.servlet.DispatcherType;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig {

// 	@Bean
// 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// 		http
//         .authorizeHttpRequests((requests) -> requests
//         .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ERROR)
//         .permitAll().anyRequest().permitAll()
//         );


// 		return http.build();
// 	}
// }