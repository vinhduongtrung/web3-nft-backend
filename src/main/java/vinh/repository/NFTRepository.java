package vinh.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vinh.dto.response.INFT;
import vinh.entity.Nft;

public interface NFTRepository extends JpaRepository<Nft, Long>{
	
	@Query("select n from Nft n where n.id = ?1")
	public INFT getNftById(Long id);
	
	@Query("select n from Nft n")
	public List<INFT> findAllNFT(Pageable pageable);
}
