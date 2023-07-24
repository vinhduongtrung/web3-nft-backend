package vinh.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenResponse {

	private Long id;
	
	private String username;
	
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("refresh_token")
	private String refreshToken;
	
	
	
	public AuthenResponse(Long id, String username, String accessToken, String refreshToken) {
		this.id = id;
		this.username = username;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
}
