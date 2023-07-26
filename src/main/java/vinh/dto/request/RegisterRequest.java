package vinh.dto.request;


public class RegisterRequest {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String repeat;
	
	

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRepeat() {
		return repeat;
	}

	@Override
	public String toString() {
		return "RegisterRequest [username=" + username + ", email=" + email + ", password=" + password + ", repeat="
				+ repeat + "]";
	}
	
}
