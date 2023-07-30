package vinh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vinh.dto.request.AddNFTRequest;
import vinh.dto.response.NFTResponse;
import vinh.dto.response.NftItemResponse;
import vinh.dto.response.TrendingResponse;
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
	
	@GetMapping("/findAllByUserName/{username}/{page}/{limit}")
	public NFTResponse findAllByUserName(
			@PathVariable("username") String username,
			@PathVariable("page") int page,
			@PathVariable("limit") int limit) {
		System.out.println("ok");
		NFTResponse response = nftItemService.findAllByUserName(username, page, limit);
		return response;
	}
	
	@GetMapping("/getMoreNft/{limit}")
	public List<NftItemResponse> getMoreNft(@PathVariable("limit") int limit) {
		System.out.println(limit);
		return nftItemService.getNftByRandomUser(limit);
	}
	
	@GetMapping("/getTrendingNft/{limit}")
	public List<TrendingResponse> getTrendingNft(@PathVariable("limit") int limit) {
		List<TrendingResponse> response = nftItemService.getTop4NftByRandomUser(limit);
		return response;
	}
	
	@GetMapping("/findAll/{page}/{limit}")
	public List<NftItemResponse> findAll(@PathVariable("page") int page,
			@PathVariable("limit") int limit) {
		System.out.println("page : " + page + ", limit : " + limit);
		List<NftItemResponse> response = nftItemService.findAll(page, limit);
		System.out.println("size : " + response.size());
		return response;
	}
	
}
