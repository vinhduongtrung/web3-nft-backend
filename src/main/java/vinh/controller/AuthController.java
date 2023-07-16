package vinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vinh.config.JwtUtil;
import vinh.dto.request.LoginRequest;
import vinh.dto.request.SignupRequest;
import vinh.dto.response.JwtResponse;
import vinh.entity.Role;
import vinh.entity.User;
import vinh.service.UserService;
import vinh.serviceImpl.CustomUserDetailService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/user/signup")
	public ResponseEntity<JwtResponse> signup(@RequestBody SignupRequest request) throws Exception {
		System.out.println("here");
		String username = request.getUsername();
		
		String email = request.getEmail();
		
		String password = request.getPassword();
		
		User user = userService.findByEmail(email);
		
		if(user != null) {
			System.out.println("user already exist with email : " + email);
			throw new Exception();
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setUsername(username);
		createdUser.setPassword(encodedPassword);
		createdUser.setRole(Role.USER);
		User savedUser = userService.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = JwtUtil.generateJwtToken(authentication);
		
		JwtResponse response = new JwtResponse(jwt, true, Role.USER);
		response.setMessage("created user successfully");
		
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
		System.out.println("login");
		String username = request.getUsername();
		String password = request.getPassword();
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = JwtUtil.generateJwtToken(authentication);
		
		JwtResponse response = new JwtResponse(jwt, true, Role.USER);
		response.setMessage("login successfully");
		
		return new ResponseEntity<JwtResponse>(response, HttpStatus.ACCEPTED);
	}
}
