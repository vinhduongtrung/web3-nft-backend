package vinh.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import vinh.entity.embeddedId.NftItemId;

@Entity
public class NftItem {
	
	@EmbeddedId
	public NftItemId id = new NftItemId();
	
	@MapsId("nft_id")
	@ManyToOne
	private Nft nft;
	
	@MapsId("artist_id")
	@ManyToOne
	private Artist artist;
	
	@ManyToOne
	private Shop shop;
	
	private double revenue;
	
	
	public NftItemId getId() {
		return id;
	}


	public void setId(NftItemId id) {
		this.id = id;
	}


	public Nft getNft() {
		return nft;
	}

	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
	}


	public void setNft(Nft nft) {
		this.nft = nft;
	}

	public Artist getArtist() {
		return artist;
	}


	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public double getRevenue() {
		return revenue;
	}


	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}	
}
