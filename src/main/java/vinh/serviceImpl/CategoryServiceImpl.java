package vinh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vinh.dto.request.AddCategoryRequest;
import vinh.dto.response.CategoryResponse;
import vinh.entity.Category;
import vinh.repository.CategoryRepository;
import vinh.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void addCategories(List<AddCategoryRequest> requests) {
		
		for(AddCategoryRequest request : requests) {
			Category category = new Category();
			category.setName(request.getName());
			category.setIcon(request.getIcon());
			category.setCover(request.getCover());
			
			categoryRepository.save(category);
		}
	}

	@Override
	public List<CategoryResponse> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryResponse> responseList = new ArrayList<>();
		for(Category category : categories) {
			CategoryResponse response = new CategoryResponse().convertEntityToResponse(category);
			responseList.add(response);
		}
		return responseList;
	}

}
