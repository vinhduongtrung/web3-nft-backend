package vinh.service;

import vinh.entity.User;

public interface UserService {
	
	public User findByEmail(String email);
	
	public User findByUsername(String username);
	
	public User save(User createdUser);
}
