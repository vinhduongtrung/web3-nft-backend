package vinh.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vinh.dto.request.AddNFTRequest;
import vinh.dto.response.INFT;
import vinh.dto.response.INftImage;
import vinh.dto.response.INftItem;
import vinh.dto.response.NFTResponse;
import vinh.dto.response.NftItemResponse;
import vinh.dto.response.TrendingResponse;
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
	public NFTResponse findAllByUserName(String username, int page, int limit) {
		User user = userRepository.findByUsername(username);
		Long userId = user.getId();
		
		Pageable pageable = PageRequest.of(page - 1, limit);
		List<INftItem> items = nftItemRepository.findAllByUserId(userId,pageable);
		
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


	@Override
	public List<NftItemResponse> getNftByRandomUser(int limit) {
		long totalUsers = userRepository.count();
		if(totalUsers == 0) {
			return null;
		}
		List<NftItemResponse> result = new ArrayList<>();
		List<User> users = getRandomUser(limit);
		for(int i = 0; i < limit; i++) {
			User user = users.get(i);
			INFT item = nftRepository.getNftById(user.getId());
			NftItemResponse response = new NftItemResponse();
			response.setUsername(user.getName());
			response.setProfilePicture(user.getProfilePicture());
			response.setImage(item.getImage());
			response.setNftName(item.getName());
			response.setBid(item.getBid());
			
			result.add(response);
		}
		
		return result;
	}
	
	private List<User> getRandomUser(long limit) {
		if (limit <= 0) {
            return Collections.emptyList();
        }

        long totalUserCount = userRepository.count();
        if (totalUserCount <= 0) {
            return Collections.emptyList();
        }

        Random random = new Random();

        Set<Long> selectedIds = new HashSet<>();

        List<User> randomUsers = new ArrayList<>();

        while (selectedIds.size() < limit && selectedIds.size() < totalUserCount) {
            long randomIndex = random.nextInt((int) totalUserCount) + 1;
            if (selectedIds.add(randomIndex)) {
                User user = userRepository.findById(randomIndex).orElse(null);
                if (user != null) {
                    randomUsers.add(user);
                }
            }
        }

        return randomUsers;
    }


	@Override
	public List<TrendingResponse> getTop3NftByRandomUser(int limit) {
		long totalUsers = userRepository.count();
		if(totalUsers == 0) {
			return null;
		}
		List<TrendingResponse> result = new ArrayList<>();
		List<User> users = getRandomUser(limit);
		for(int i = 0; i < limit; i++) {
			User user = users.get(i);
			List<INftImage> nfts = nftItemRepository.findTop3NftByUserId(user.getId(), PageRequest.of(0, 3));
			TrendingResponse response = new TrendingResponse();
			response.setUsername(user.getName());
			response.setProfile(user.getProfilePicture());
			response.setData(nfts);
			result.add(response);
		}
		return result;
	}

}
