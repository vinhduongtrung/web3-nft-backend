package vinh.service;

import java.util.List;

import vinh.dto.request.AddNFTRequest;
import vinh.dto.response.NFTResponse;

public interface NFTItemService {
	
	public void add(List<AddNFTRequest> items);
	
	public NFTResponse findAllByUserName(String username, int page, int limit);
}
