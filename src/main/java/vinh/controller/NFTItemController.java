package vinh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vinh.dto.request.AddNFTRequest;
import vinh.service.NFTItemService;

@RestController
@RequestMapping("/api/v1/nft")
public class NFTItemController {
	
	@Autowired
	private NFTItemService nftItemService;
	
	@PostMapping("/add")
	public void addNFT(@RequestBody List<AddNFTRequest> items) {
		nftItemService.add(items);
	}
}
