package vinh.dto.request;

import java.util.List;

public class UpdateUserRequest {
	
	private String username;
	
	private String profilePicture;
	
	private String bio;
	
	private List<String> socialLinks;

	
	public String getUsername() {
		return username;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public String getBio() {
		return bio;
	}

	public List<String> getSocialLinks() {
		return socialLinks;
	}
}
