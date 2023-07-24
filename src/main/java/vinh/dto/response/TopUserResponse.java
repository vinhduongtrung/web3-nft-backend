package vinh.dto.response;

public class TopUserResponse {

	public String username;

	public String profile;

	public int totalSales;

	public TopUserResponse(String username, String profile, int totalSales) {
		this.username = username;
		this.profile = profile;
		this.totalSales = totalSales;
	}

	public String getUsername() {
		return username;
	}

	public String getProfile() {
		return profile;
	}

	public int getTotalSales() {
		return totalSales;
	}
}
