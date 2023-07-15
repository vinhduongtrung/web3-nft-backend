package vinh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
	
	
	@GetMapping("/get")
	public String get() {
		return "artist";
	}

}
