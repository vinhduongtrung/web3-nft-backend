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
	
	@MapsId("user_id")
	@ManyToOne
	private User user;
	
	
	public Long getUserId() {
		return user.getId();
	}
	
	public Long getNftId() {
		return nft.getId();
	}
	
	public NftItemId getId() {
		return id;
	}
	public void setId(NftItemId id) {
		this.id = id;
	}


	public Nft getNft() {
		return nft;
	}


	public void setNft(Nft nft) {
		this.nft = nft;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
