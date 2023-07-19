package vinh.entity.embeddedId;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class NftItemId implements Serializable{

	private static final long serialVersionUID = 1L;

	public Long nft_id;
	
	public Long user_id;

	@Override
	public int hashCode() {
		return Objects.hash(nft_id, user_id);
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
		return Objects.equals(nft_id, other.nft_id) && Objects.equals(user_id, other.user_id);
	}
}