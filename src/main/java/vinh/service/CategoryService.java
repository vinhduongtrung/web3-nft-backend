package vinh.service;

import java.util.List;

import vinh.dto.request.AddCategoryRequest;
import vinh.dto.response.CategoryResponse;

public interface CategoryService {
	
	public void addCategories(List<AddCategoryRequest> requests);
	
	public List<CategoryResponse> getCategories();
}
