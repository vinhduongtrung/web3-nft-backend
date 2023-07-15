package vinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import vinh.dto.response.JwtResponse;

@RestController
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;

}
