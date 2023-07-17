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
public class AuthenticationController {
	
	@Autowired
	private AuthenService service;
	
	@PostMapping("/register")
	  public ResponseEntity<AuthenResponse> register(@RequestBody RegisterRequest request) {
	    return ResponseEntity.ok(service.register(request));
	  }
	@PostMapping("/authenticate")
	  public ResponseEntity<AuthenResponse> authenticate(@RequestBody AuthenRequest request) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

	 @PostMapping("/refresh-token")
	  public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
	    service.refreshToken(request, response);
	  }
}
