package vinh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityConfiguration(HttpSecurity http)
	throws Exception {
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
		.requestMatchers(HttpMethod.GET,"/swagger-ui/**","/").permitAll()
		.requestMatchers(HttpMethod.GET,"/ws/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(null, null);
		return null;
	}
}
