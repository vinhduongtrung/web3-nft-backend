package vinh.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



public class JwtUtil {
	
	static SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());
	
	public static String generateJwtToken(Authentication authentication) {
		String jwt = Jwts.builder()
				.setIssuer("vinh")
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+ 86400000))
				.claim("email", authentication.getName())
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	public String getEmailFromJwt(String jwt) {
		jwt = jwt.substring(7);
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt)
				.getBody();
		String email = String.valueOf(claims.get("email"));
		return email;
	}
	
}
