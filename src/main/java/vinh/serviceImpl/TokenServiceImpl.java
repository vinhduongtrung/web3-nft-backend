package vinh.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vinh.entity.token.Token;
import vinh.repository.TokenRepository;
import vinh.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public List<Token> findAllValidTokenByUser(Long id) {
		return tokenRepository.findAllValidTokenByUser(id);
	}

	@Override
	public Optional<Token> findByToken(String token) {
		return tokenRepository.findByToken(token);
	}

}
