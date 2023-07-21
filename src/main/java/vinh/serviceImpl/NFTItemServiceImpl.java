package vinh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vinh.dto.request.AddNFTRequest;
import vinh.dto.response.INFT;
import vinh.dto.response.INftItem;
import vinh.dto.response.NFTResponse;
import vinh.entity.Category;
import vinh.entity.Nft;
import vinh.entity.NftItem;
import vinh.entity.User;
import vinh.entity.embeddedId.NftItemId;
import vinh.repository.CategoryRepository;
import vinh.repository.NFTItemRepository;
import vinh.repository.NFTRepository;
import vinh.repository.UserRepository;
import vinh.service.NFTItemService;

@Service
public class NFTItemServiceImpl implements NFTItemService {

	@Autowired
	private NFTItemRepository nftItemRepository;
	
	@Autowired
	private NFTRepository nftRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public void add(List<AddNFTRequest> items) {
		for(AddNFTRequest item : items) {
			Long userId = item.getUserId();
			User user = userRepository.findById(userId).get();
			Category category = categoryRepository.findByName(item.getCategoryName());
			if(category == null) {
				System.out.println("category is null");
				return;
			}
			Nft nft = new Nft();
			nft.setName(item.getName());
			nft.setImage(item.getImage());
			nft.setPrice(item.getPrice());
			nft.setCategory(category);
			Nft savedNFT = nftRepository.save(nft);
			
			NftItem nftItem = new NftItem();
			NftItemId id = new NftItemId(nft.getId(), userId);
			nftItem.setId(id);
			nftItem.setNft(savedNFT);
			nftItem.setUser(user);
			
			nftItemRepository.save(nftItem);
			
		}
		
	}


	@Override
	public NFTResponse findAllByUserName(String username) {
		User user = userRepository.findByUsername(username);
		Long userId = user.getId();
		List<INftItem> items = nftItemRepository.findAllByUserId(userId);
		NFTResponse response = new NFTResponse();
		response.setUsername(username);
		response.setProfilePicture(user.getProfilePicture());
		List<INFT> list = new ArrayList<>();
		for(INftItem item : items) {
			Long nftId = item.getNftId();
			
			INFT nft = nftRepository.getNftById(nftId);
			list.add(nft);
		}
		response.setNft(list);
		return response;
	}

}
