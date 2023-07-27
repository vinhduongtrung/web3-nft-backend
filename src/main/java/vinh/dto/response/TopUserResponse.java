package vinh.dto.response;

public class TopUserResponse {
	
	private Long id;

	private String username;

	private String profile;

	private int totalSales;

	public TopUserResponse(Long id, String username, String profile, int totalSales) {
		this.id = id;
		this.username = username;
		this.profile = profile;
		this.totalSales = totalSales;
	}

	
	public Long getId() {
		return id;
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
