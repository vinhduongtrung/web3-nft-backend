package vinh.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
	
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	
	private String password;
	
	

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
