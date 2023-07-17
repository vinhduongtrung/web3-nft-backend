package vinh.service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.request.AuthenRequest;
import vinh.dto.request.RegisterRequest;
import vinh.dto.response.AuthenResponse;
import vinh.entity.User;

public interface AuthenService {
	
	public AuthenResponse register(RegisterRequest request);
	
	public AuthenResponse authenticate(AuthenRequest request);
	
	public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException;
	
	public void saveUserToken(User user, String jwtToken);
	
	public void revokeAllUserTokens(User user);
}
