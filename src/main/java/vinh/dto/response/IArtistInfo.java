package vinh.dto.response;

import java.util.List;

public interface IArtistInfo {
	
	public String getName();
	
	public String getBio();
	
	public String getProfilePicture();
	
	public int getFollow();
	
	public List<String> getSocialLinks();
}
