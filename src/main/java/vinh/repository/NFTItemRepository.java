package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.NftItem;
import vinh.entity.embeddedId.NftItemId;

public interface NFTItemRepository extends JpaRepository<NftItem, NftItemId> {

}
