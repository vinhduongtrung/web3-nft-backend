package vinh.dto.request;

public class AddNFTRequest {
	
	private Long userId;
	
	private String name;
	
	private String image;
	
	private double price;
	
	private Long categoryId;
	
	

	
	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public double getPrice() {
		return price;
	}

	public Long getCategoryId() {
		return categoryId;
	}
}
