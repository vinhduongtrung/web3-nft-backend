package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
	public Artist findByEmail(String email);
	
	public Artist findByUsername(String username);
}
