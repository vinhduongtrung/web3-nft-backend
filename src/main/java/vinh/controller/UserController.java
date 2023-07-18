package vinh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.request.AuthenRequest;
import vinh.dto.request.RegisterRequest;
import vinh.dto.response.AuthenResponse;
import vinh.dto.response.UserResponse;
import vinh.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/user/register")
	  public ResponseEntity<AuthenResponse> register(@RequestBody RegisterRequest request) {
	    return ResponseEntity.ok(service.register(request));
	  }
	@PostMapping("/user/login")
	  public ResponseEntity<AuthenResponse> login(@RequestBody AuthenRequest request) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

	@PostMapping("/user/refresh-token")
	  public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
	    service.refreshToken(request, response);
	  }
	@PostMapping("/user/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		service.logout(request, response, null);
	}
	
	@GetMapping("/user/find/{id}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable("id") Long userId) {
		return ResponseEntity.ok().body(service.findUserById(userId));
	}
}
