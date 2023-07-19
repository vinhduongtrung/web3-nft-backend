package vinh.service;

import java.util.List;

import vinh.dto.request.AddNFTRequest;

public interface NFTItemService {
	
	public void add(List<AddNFTRequest> items);
}
