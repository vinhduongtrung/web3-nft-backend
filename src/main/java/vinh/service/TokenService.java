package vinh.service;

import java.util.List;
import java.util.Optional;

import vinh.entity.token.Token;

public interface TokenService {
	
	List<Token> findAllValidTokenByUser(Long id);
	
	Optional<Token> findByToken(String token);
}
