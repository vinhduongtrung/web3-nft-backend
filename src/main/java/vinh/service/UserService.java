package vinh.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.request.AuthenRequest;
import vinh.dto.request.RegisterRequest;
import vinh.dto.response.AuthenResponse;
import vinh.dto.response.UserResponse;
import vinh.entity.User;

public interface UserService {
	
	public AuthenResponse register(RegisterRequest request);
	
	public AuthenResponse authenticate(AuthenRequest request);
	
	public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException;
	
	public void saveUserToken(User user, String jwtToken);
	
	public void revokeAllUserTokens(User user);
	
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
	
	public UserResponse findUserById(Long userId);
	
	public Optional<User> findByEmail(String email);
}
