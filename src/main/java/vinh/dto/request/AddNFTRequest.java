package vinh.dto.request;

public class AddNFTRequest {
	
	private Long userId;
	
	private String name;
	
	private String image;
	
	private String price;
	
	private String categoryName;
	
	

	
	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public String getPrice() {
		return price;
	}

	public String getCategoryName() {
		return categoryName;
	}
}
