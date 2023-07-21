package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vinh.dto.response.INFT;
import vinh.entity.Nft;

public interface NFTRepository extends JpaRepository<Nft, Long>{
	
	@Query("select n from Nft n where n.id = ?1")
	public INFT getNftById(Long id);
}
