package vinh.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vinh.entity.User;
import vinh.repository.UserRepository;
import vinh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User createdUser) {
		return userRepository.save(createdUser);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}