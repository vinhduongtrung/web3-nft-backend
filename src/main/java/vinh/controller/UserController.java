package vinh.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@GetMapping("/get")
	public String get() {
		return "user";
	}
	
	@PostMapping("/post")
    public String post() {
        return "POST:: user controller";
    }
    @PutMapping("/put")
    public String put() {
        return "PUT:: user controller";
    }
    @DeleteMapping("/delete")
    public String delete() {
        return "DELETE:: user controller";
    }
}
