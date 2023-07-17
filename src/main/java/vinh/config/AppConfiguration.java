package vinh.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Configuration
//public class AppConfiguration {
//	
//	@Bean
//	public SecurityFilterChain appConfig(HttpSecurity http) throws Exception {
//		System.out.println("AppConfiguration");
//		http.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.authorizeHttpRequests()
//		.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
//		.requestMatchers("/v3/api-docs/**",
//		        "/swagger-ui/**",
//		        "/swagger-ui.html").permitAll()
//		.requestMatchers(HttpMethod.GET,"/ws/**").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.addFilterBefore(new JwtTokenVadidationFilter(), BasicAuthenticationFilter.class)
//		.csrf().disable()
//		.cors()
//		.configurationSource(new CorsConfigurationSource() {
//			
//			@Override
//			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//				CorsConfiguration cfg = new CorsConfiguration();
//				cfg.setAllowedOrigins(Arrays.asList(
//					"http://localhost:5173"
//				));
//				cfg.setAllowedMethods(Collections.singletonList("*"));
//				cfg.setAllowCredentials(true);
//				cfg.setAllowedHeaders(Collections.singletonList("*"));
//				cfg.setExposedHeaders(Arrays.asList("Authorization"));
//				cfg.setMaxAge(3600L);
//				return cfg;
//			}
//		})
//		.and()
//		.httpBasic()
//		.and()
//		.formLogin();
//		
//		return http.build();
//	}
//	
//	
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import vinh.service.UserService;

@Configuration
public class AppConfiguration {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return username -> userService.findByEmail(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	  }

	  @Bean
	  public AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	  }

	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

	
}