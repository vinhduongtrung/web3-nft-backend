package vinh.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.response.UserResponse;
import vinh.entity.User;

public interface UserService {
	
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
	
	public UserResponse findUserById(Long userId);
	
	public Optional<User> findByEmail(String email);
}
