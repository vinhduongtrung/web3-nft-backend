package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
