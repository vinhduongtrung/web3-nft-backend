package vinh.dto.response;

import java.util.List;

public class NFTResponse {
	
	private String username;
	
	private String profilePicture;
	
	private List<INFT> nft;

	
	
	public String getUsername() {
		return username;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public List<INFT> getNft() {
		return nft;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setNft(List<INFT> nft) {
		this.nft = nft;
	}

}
