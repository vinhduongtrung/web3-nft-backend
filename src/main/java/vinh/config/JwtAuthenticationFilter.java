package vinh.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.service.TokenService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (request.getServletPath().contains("/api/v1/auth")) {
		      filterChain.doFilter(request, response);
		      return;
		    }
		    final String authHeader = request.getHeader("Authorization");
		    final String jwt;
		    final String userEmail;
		    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
		      filterChain.doFilter(request, response);
		      return;
		    }
		    jwt = authHeader.substring(7);
		    userEmail = jwtService.extractUsername(jwt);
		    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		    	System.out.println("jwt invalid");
		      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
		      var isTokenValid = tokenService.findByToken(jwt)
		          .map(t -> !t.isExpired() && !t.isRevoked())
		          .orElse(false);
		      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
		        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
		            userDetails,
		            null,
		            userDetails.getAuthorities()
		        );
		        authToken.setDetails(
		            new WebAuthenticationDetailsSource().buildDetails(request)
		        );
		        SecurityContextHolder.getContext().setAuthentication(authToken);
		      }
		    }
		    filterChain.doFilter(request, response);
		  }
}
