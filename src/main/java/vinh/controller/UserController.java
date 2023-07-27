package vinh.controller;

import java.util.List;

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
import vinh.dto.request.UpdateUserRequest;
import vinh.dto.response.ArtistInfoResponse;
import vinh.dto.response.TopUserResponse;
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
	
	@GetMapping("/getArtistInfo/{username}")
	public ResponseEntity<ArtistInfoResponse> getArtistInfo(@PathVariable("username") String username) {
		return ResponseEntity.ok().body(userService.getArtistInfo(username));
	}
	
	@PostMapping("/update")
	public void updateUser(@RequestBody List<UpdateUserRequest> requests) {
		userService.updateUser(requests);
	}
	
	@GetMapping("/getTopUser/{page}/{limit}")
	public List<TopUserResponse> getTopUserByQuantitySold(@PathVariable("page") int page,@PathVariable("limit") int limit) {
		return userService.findTopUserByTotalSales(page, limit);
	}
}
