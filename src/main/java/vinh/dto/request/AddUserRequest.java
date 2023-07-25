package vinh.dto.request;

public class AddUserRequest {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String profilePicture;
	
	private String bio;
	
	private double volume;

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

	public String getBio() {
		return bio;
	}

	public double getVolume() {
		return volume;
	}
}
