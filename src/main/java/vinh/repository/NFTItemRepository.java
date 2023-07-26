package vinh.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vinh.dto.response.INftImage;
import vinh.dto.response.INftItem;
import vinh.entity.NftItem;
import vinh.entity.embeddedId.NftItemId;

public interface NFTItemRepository extends JpaRepository<NftItem, NftItemId> {
	
	@Query("select n from NftItem n where n.id.user_id = ?1")
	public List<INftItem> findAllByUserId(Long userId, Pageable pageable);
	
	
	@Query("select n.nft.id as id, n.nft.image as image, n.nft.name as name from NftItem n where n.id.user_id = ?1")
	public List<INftImage> findTop3NftByUserId(Long userId, Pageable pageable);
}
