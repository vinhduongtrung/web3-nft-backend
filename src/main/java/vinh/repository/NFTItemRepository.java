package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.Nft;
import vinh.entity.embeddedId.NftItemId;

public interface NFTItemRepository extends JpaRepository<Nft, NftItemId>{

}
