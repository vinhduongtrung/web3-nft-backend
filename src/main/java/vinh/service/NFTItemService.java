package vinh.service;

import java.util.List;

import vinh.dto.request.AddNFTRequest;
import vinh.dto.response.NFTResponse;
import vinh.dto.response.NftItemResponse;

public interface NFTItemService {
	
	public void add(List<AddNFTRequest> items);
	
	public NFTResponse findAllByUserName(String username, int page, int limit);
	
	public List<NftItemResponse> getNftByRandomUser(int limit);
}
