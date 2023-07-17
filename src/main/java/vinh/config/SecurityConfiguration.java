package vinh.config;
import static vinh.entity.Role.ARTIST;
import static vinh.entity.Role.USER;

import static vinh.entity.Permission.ARTIST_READ;
import static vinh.entity.Permission.ARTIST_UPDATE;
import static vinh.entity.Permission.ARTIST_CREATE;
import static vinh.entity.Permission.ARTIST_DELETE;
import static vinh.entity.Permission.USER_READ;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

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


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthFilter;
//	@Autowired
//	private AuthenticationProvider authenticationProvider;
	
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers(
	                "/api/v1/auth/**",
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


	        .requestMatchers("/api/v1/user/**").hasAnyRole(ARTIST.name(), USER.name())


	        .requestMatchers(GET, "/api/v1/user/**").hasAnyAuthority(ARTIST_READ.name(), USER_READ.name())
	        .requestMatchers(POST, "/api/v1/user/**").hasAnyAuthority(ARTIST_CREATE.name())
	        .requestMatchers(PUT, "/api/v1/user/**").hasAnyAuthority(ARTIST_UPDATE.name())
	        .requestMatchers(DELETE, "/api/v1/user/**").hasAnyAuthority(ARTIST_DELETE.name())


	       /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())

	        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
	        .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
	        .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
	        .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/


	        .anyRequest()
	          .authenticated()
	        .and()
	          .sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	        .and()
//	        .authenticationProvider(authenticationProvider)
//	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//	        .logout()
//	        .logoutUrl("/api/v1/auth/logout")
//	        .addLogoutHandler(logoutHandler)
//	        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
	    ;

	    return http.build();
	  }
}
