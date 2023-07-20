package vinh.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.request.UpdateUserRequest;
import vinh.dto.response.ArtistInfoResponse;
import vinh.dto.response.IArtistInfo;
import vinh.dto.response.IShopInfo;
import vinh.dto.response.UserResponse;
import vinh.entity.User;
import vinh.repository.TokenRepository;
import vinh.repository.UserRepository;
import vinh.service.UserService;

@Service
public class UserServiceImpl implements UserService, LogoutHandler {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public UserResponse findUserById(Long userId) {
		User user = userRepository.findById(userId).get();
		return new UserResponse(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		final String authHeader = request.getHeader("Authorization");
	    final String jwt;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    jwt = authHeader.substring(7);
	    var storedToken = tokenRepository.findByToken(jwt)
	        .orElse(null);
	    if (storedToken != null) {
	      storedToken.setExpired(true);
	      storedToken.setRevoked(true);
	      tokenRepository.save(storedToken);
	      SecurityContextHolder.clearContext();
	    }
	 }

	@Override
	public ArtistInfoResponse getArtistInfo(String username) {
		
		IArtistInfo artistInfo = userRepository.getArtistInfo(username);
		
		IShopInfo shopInfo = userRepository.getShopInfo(username);
		
		ArtistInfoResponse response = new ArtistInfoResponse();
		response.setArtistInfo(artistInfo);
		response.setShopInfo(shopInfo);
		
		return response;
	}

	@Override
	public void updateUser(List<UpdateUserRequest> requests) {
		for(UpdateUserRequest request : requests) {
			User savedUser = userRepository.findByUsername(request.getUsername());
			savedUser.setProfilePicture(request.getProfilePicture());
			savedUser.setBio(request.getBio());
			savedUser.setSocialLinks(request.getSocialLinks());
			
			userRepository.save(savedUser);
		}
		
	}
}
