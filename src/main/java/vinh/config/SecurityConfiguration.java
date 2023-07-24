package vinh.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private LogoutHandler logoutHandler;
	
	@SuppressWarnings("removal")
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers(
	                "/api/v1/auth/**",
	                "/api/v1/category/**",
	                "/api/v1/nft/**",
	                "/api/v1/user/**",
	                "/v2/api-docs",
	                "/v3/api-docs",
	                "/v3/api-docs/**",
	                "/swagger-resources",
	                "/swagger-resources/**",
	                "/configuration/ui",
	                "/configuration/security",
	                "/swagger-ui/**",
	                "/webjars/**",
	                "/swagger-ui.html"
	        )
	          .permitAll()
	  	
	        .anyRequest()
	          .authenticated()
	          .and()
		  		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		  		.csrf().disable()
		  		.cors()
		  		.configurationSource(new CorsConfigurationSource() {
		  			
		  			@Override
		  			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		  				CorsConfiguration cfg = new CorsConfiguration();
		  				cfg.setAllowedOrigins(Arrays.asList(
		  					"http://localhost:5173"
		  				));
		  				cfg.setAllowedMethods(Collections.singletonList("*"));
		  				cfg.setAllowCredentials(true);
		  				cfg.setAllowedHeaders(Collections.singletonList("*"));
		  				cfg.setExposedHeaders(Arrays.asList("Authorization"));
		  				cfg.setMaxAge(3600L);
		  				return cfg;
		  			}
		  		})
		  		.and()
				.httpBasic()
				.and()
				.formLogin()
	        .and()
	          .sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .logout()
	        .logoutUrl("/api/v1/user/logout")
	        .addLogoutHandler(logoutHandler)
	        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
	    ;

	    return http.build();
	  }
}
