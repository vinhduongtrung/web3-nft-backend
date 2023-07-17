package vinh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
	
	public User findByUsername(String username);
}
