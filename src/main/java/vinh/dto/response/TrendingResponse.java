package vinh.dto.response;

import java.util.List;

public class TrendingResponse {
	
	public String username;

	public String profile;
	
	List<INftImage> data;

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public List<INftImage> getData() {
		return data;
	}

	public void setData(List<INftImage> data) {
		this.data = data;
	}
	
}
