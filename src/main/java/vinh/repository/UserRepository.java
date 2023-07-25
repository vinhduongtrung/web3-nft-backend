package vinh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vinh.dto.response.IArtistInfo;
import vinh.dto.response.IShopInfo;
import vinh.dto.response.IUserInfo;
import vinh.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
	
	public User findByUsername(String username);
	
	@Query("select u from User u where u.username = ?1")
	public IArtistInfo getArtistInfo(String username);
	
	@Query("select s from Shop s where s.user.username = ?1")
	public IShopInfo getShopInfo(String username);
	
	@Query("select u from User u where u.username = ?1")
	public IUserInfo getUserInfo(String username);
	
	@Query("select u.username, u.profilePicture as profile, s.sold as totalSales from Shop s " +
			"JOIN s.user u " +
			"GROUP BY u.id " +
			"ORDER BY totalSales DESC"
			)
	public List<Object[]> findTopUserByTotalSales(Pageable pageable);
}
