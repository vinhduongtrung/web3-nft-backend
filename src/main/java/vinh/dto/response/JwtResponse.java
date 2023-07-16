package vinh.dto.response;

import vinh.entity.Role;

public class JwtResponse {

	private String jwt;
	private String message;
	private boolean isAuthenticated;
	private Role type;
	
	
	
	
	public JwtResponse(String jwt, boolean isAuthenticated, Role type) {
		this.jwt = jwt;
		this.isAuthenticated = isAuthenticated;
		this.type = type;
	}
	
	public String getJwt() {
		return jwt;
	}
	public String getMessage() {
		return message;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public Role getType() {
		return type;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
