package vinh.dto.response;

public class ArtistInfoResponse {
	
	private IArtistInfo artistInfo;
	
	private IShopInfo shopInfo;

	
	
	public IArtistInfo getArtistInfo() {
		return artistInfo;
	}

	public IShopInfo getShopInfo() {
		return shopInfo;
	}

	public void setArtistInfo(IArtistInfo artistInfo) {
		this.artistInfo = artistInfo;
	}

	public void setShopInfo(IShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
}
