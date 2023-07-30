package vinh.dto.response;

public class NftItemResponse {
	
	private String username;
	
	private String profilePicture;
	
	private String image;
	
	private Long id;
	
	private String nftName;
	
	private double price;
	
	private double bid;

	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setNftName(String nftName) {
		this.nftName = nftName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public String getUsername() {
		return username;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public String getImage() {
		return image;
	}

	public String getNftName() {
		return nftName;
	}

	public double getPrice() {
		return price;
	}

	public double getBid() {
		return bid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
