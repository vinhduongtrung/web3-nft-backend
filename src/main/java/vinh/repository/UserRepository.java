package vinh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
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
	
	@Query("select s from Collection s where s.user.username = ?1")
	public IShopInfo getShopInfo(String username);
	
	@Query("select u from User u where u.username = ?1")
	public IUserInfo getUserInfo(String username);
	
	@Query("select u.id, u.username, u.profilePicture as profile, s.sold as totalSales from Collection s " +
			"JOIN s.user u "
			)
	public List<Object[]> findTopUserByTotalSales(Pageable pageable);
	
	public Page<User> findById(Long randomId, Pageable pageable);
}
