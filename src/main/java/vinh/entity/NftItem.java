package vinh.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class NftItem {
	
	@EmbeddedId
	public NftItemId id = new NftItemId();
	
	@MapsId("nft_id")
	@ManyToOne
	private Nft nft;
	
	@MapsId("artist_id")
	@ManyToOne
	private User artist;
	
	@ManyToOne
	private ArtistShop shop;
	
	private int totalSold;
	
	
	public NftItemId getId() {
		return id;
	}


	public void setId(NftItemId id) {
		this.id = id;
	}


	public Nft getNft() {
		return nft;
	}

	public ArtistShop getShop() {
		return shop;
	}


	public void setShop(ArtistShop shop) {
		this.shop = shop;
	}


	public void setNft(Nft nft) {
		this.nft = nft;
	}

	public User getArtist() {
		return artist;
	}


	public void setArtist(User artist) {
		this.artist = artist;
	}


	public int getTotalSold() {
		return totalSold;
	}


	public void setTotalSold(int totalSold) {
		this.totalSold = totalSold;
	}


	@Embeddable
	public class NftItemId implements Serializable{
	
		private static final long serialVersionUID = 1L;

		public Long nft_id;
		
		public Long artist_id;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(artist_id, nft_id);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NftItemId other = (NftItemId) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(artist_id, other.artist_id) && Objects.equals(nft_id, other.nft_id);
		}

		private NftItem getEnclosingInstance() {
			return NftItem.this;
		}
	}
	
}
