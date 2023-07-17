package vinh.service;

import java.util.Optional;

import vinh.entity.User;

public interface UserService {
	
	public Optional<User> findByEmail(String email);
	
	public User findByUsername(String username);
	
	public User save(User createdUser);
}
