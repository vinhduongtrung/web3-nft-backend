package vinh.dto.response;

public class JwtResponse {

	private String jwt;
	private String message;
	private boolean isAuthenticated;
	
	
	
	public String getJwt() {
		return jwt;
	}
	public String getMessage() {
		return message;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
}
