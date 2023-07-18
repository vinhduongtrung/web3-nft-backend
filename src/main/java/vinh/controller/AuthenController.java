package vinh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.request.AuthenRequest;
import vinh.dto.request.RegisterRequest;
import vinh.dto.response.AuthenResponse;
import vinh.service.AuthenService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenController {
	
	@Autowired
	private AuthenService authenService;
	
	@PostMapping("/register")
	  public ResponseEntity<AuthenResponse> register(@RequestBody RegisterRequest request) {
	    return ResponseEntity.ok(authenService.register(request));
	  }
	@PostMapping("/login")
	  public ResponseEntity<AuthenResponse> login(@RequestBody AuthenRequest request) {
	    return ResponseEntity.ok(authenService.authenticate(request));
	  }

	@PostMapping("/refresh-token")
	  public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
		authenService.refreshToken(request, response);
	  }
}
