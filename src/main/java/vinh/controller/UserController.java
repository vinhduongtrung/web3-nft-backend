package vinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vinh.dto.response.UserResponse;
import vinh.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		userService.logout(request, response, null);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable("id") Long userId) {
		return ResponseEntity.ok().body(userService.findUserById(userId));
	}
}
