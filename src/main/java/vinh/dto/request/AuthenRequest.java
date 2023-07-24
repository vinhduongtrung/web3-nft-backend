package vinh.dto.request;

public class AuthenRequest {
	
	private String email;
	
	private String password;
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "AuthenRequest [email=" + email + ", password=" + password + "]";
	}
	
	
}
