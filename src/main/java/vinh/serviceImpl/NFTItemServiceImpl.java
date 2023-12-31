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
import vinh.dto.response.IUserInfo;
import vinh.dto.response.NFTId;
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
			Long userId = user.getId();
			System.out.println("userId : " + userId);
			List<NFTId> idList = nftItemRepository.getNftIdByUserId(user.getId()); //// fix
			int size = idList.size();
			System.out.println("size: " + size);
			if (size <= 1) {
	            throw new IllegalArgumentException("size must be greater than 1.");
	        }
			int randomIndex = new Random().nextInt(size) + 1;
			Long id = idList.get(randomIndex).getId();
			System.out.println("id : " + id);
			
			INFT item = nftRepository.getNftById(id);
			if(item == null) {
				System.out.println("no nft was found by id : " + id);
			}
			NftItemResponse response = new NftItemResponse();
			response.setUsername(user.getName());
			response.setProfilePicture(user.getProfilePicture());
			response.setImage(item.getImage());
			response.setNftName(item.getName());
			response.setBid(item.getBid());
			response.setPrice(item.getPrice());
			response.setId(item.getId());
			
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
	public List<TrendingResponse> getTop4NftByRandomUser(int limit) {
		long totalUsers = userRepository.count();
		if(totalUsers == 0) {
			return null;
		}
		List<TrendingResponse> result = new ArrayList<>();
		List<User> users = getRandomUser(limit);
		for(int i = 0; i < limit; i++) {
			User user = users.get(i);
			List<INftImage> nfts = nftItemRepository.findTop3NftByUserId(user.getId(), PageRequest.of(0, 4));
			TrendingResponse response = new TrendingResponse();
			response.setUsername(user.getName());
			response.setProfile(user.getProfilePicture());
			response.setData(nfts);
			result.add(response);
		}
		return result;
	}


	@Override
	public List<NftItemResponse> findAll(int page, int limit) {
		List<INFT> nfts = nftRepository.findAllNFT(PageRequest.of(page - 1, limit));
		List<NftItemResponse> result = new ArrayList<>();
		
		for(INFT nft : nfts) {
			Long userId = nftItemRepository.getUserIdByNftId(nft.getId()).getId();
			IUserInfo userInfo = userRepository.getUserById(userId);
			NftItemResponse response = new NftItemResponse();
			response.setUsername(userInfo.getName());
			response.setProfilePicture(userInfo.getProfilePicture());
			response.setImage(nft.getImage());
			response.setId(nft.getId());
			response.setNftName(nft.getName());
			response.setPrice(nft.getPrice());
			response.setBid(nft.getBid());
			result.add(response);
		}
		return result;
	}

}
