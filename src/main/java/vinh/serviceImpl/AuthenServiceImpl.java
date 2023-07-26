package vinh.serviceImpl;

import static vinh.entity.Role.USER;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.config.JwtService;
import vinh.dto.request.AddUserRequest;
import vinh.dto.request.AuthenRequest;
import vinh.dto.request.RegisterRequest;
import vinh.dto.response.AuthenResponse;
import vinh.entity.Collection;
import vinh.entity.User;
import vinh.entity.token.Token;
import vinh.entity.token.TokenType;
import vinh.repository.CollectionRepository;
import vinh.repository.TokenRepository;
import vinh.repository.UserRepository;
import vinh.service.AuthenService;

@Service
public class AuthenServiceImpl implements AuthenService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CollectionRepository collectionRepository;

	@Override
	public AuthenResponse register(RegisterRequest request) {
		System.out.println(request);
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(USER);
		
		User savedUser = repository.save(user);
		Collection collection = new Collection();
		collection.setUser(savedUser);
		collection.setBackground("https://cdn.animaapp.com/projects/63aaf7e2426e9824f0350c11/releases/63aaf8f2426e9824f0350c13/img/image-placeholder-7@1x.png");
		collectionRepository.save(collection);
		
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUser, jwtToken);
		
		return new AuthenResponse(user.getId(),user.getName(),jwtToken, refreshToken);
	}

	@Override
	public AuthenResponse authenticate(AuthenRequest request) {
		authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			            request.getEmail(),
			            request.getPassword()
			        )
			    );
		User user = repository.findByEmail(request.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return new AuthenResponse(user.getId(),user.getName(),jwtToken, refreshToken);
	}
	
	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      User user = this.repository.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        String accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        AuthenResponse authResponse = new AuthenResponse(user.getId(),user.getName(),accessToken, refreshToken);
	               
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
		
	}
	@Override
	public void saveUserToken(User user, String jwtToken) {
	    Token token = new Token();
	    token.setUser(user);
	    token.setToken(jwtToken);
	    token.setTokenType(TokenType.BEARER);
	    token.setExpired(false);
	    token.setRevoked(false);
	    tokenRepository.save(token);
	  }
	@Override
	  public void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }
	
	
	@Override
	public void insertDummyUser(List<AddUserRequest> requests) {
		for(AddUserRequest request : requests) {
			User user = new User();
			user.setUsername(request.getUsername());
			user.setEmail(request.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			user.setBio(request.getBio());
			user.setProfilePicture(request.getProfilePicture());
			user.setRole(USER);
			
			User savedUser = repository.save(user);
			Collection collection = new Collection();
			collection.setUser(savedUser);
			collection.setName(request.getUsername() + " collection");
			collection.setBackground("https://cdn.animaapp.com/projects/63aaf7e2426e9824f0350c11/releases/63aaf8f2426e9824f0350c13/img/image-placeholder-7@1x.png");
			collection.setVolume(request.getVolume());
			collectionRepository.save(collection);
			
			String jwtToken = jwtService.generateToken(user);
			saveUserToken(savedUser, jwtToken);
		}
	}
}
