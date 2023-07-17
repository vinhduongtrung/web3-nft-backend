package vinh.entity.token;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import vinh.entity.User;

@Entity
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	private TokenType tokenType = TokenType.BEARER;
	
	private boolean revoked;

	private boolean expired;

	@ManyToOne
	private User user;

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
