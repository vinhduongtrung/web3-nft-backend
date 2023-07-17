package vinh.dto.request;

import vinh.entity.Role;

public class RegisterRequest {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Role role;
	
	

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}
}
