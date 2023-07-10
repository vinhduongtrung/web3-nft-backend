package vinh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static vinh.entity.Role.USER;
import static vinh.entity.Role.ARTIST;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers(
	                "/api/**",
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
	          .requestMatchers("/api/**").hasAnyRole(ARTIST.name())
	          .requestMatchers(GET, "/api/**").hasAnyAuthority(ARTIST.name())
	          .anyRequest()
	          .authenticated();
	    
	    return http.build();
	  }
}
