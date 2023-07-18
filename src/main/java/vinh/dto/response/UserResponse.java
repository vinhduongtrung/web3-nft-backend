package vinh.dto.response;

import vinh.entity.User;

public class UserResponse {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String profilePicture;
	
	private String role;

	
	public UserResponse(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.profilePicture = user.getProfilePicture();
		this.role = user.getRole().name();
	}
	
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public String getRole() {
		return role;
	}
	
	
}
