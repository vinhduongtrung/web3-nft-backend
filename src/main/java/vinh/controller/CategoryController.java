package vinh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vinh.dto.request.AddCategoryRequest;
import vinh.dto.response.CategoryResponse;
import vinh.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public void addCategories(@RequestBody List<AddCategoryRequest> requests) {
		categoryService.addCategories(requests);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryResponse>> getCategories() {
		return null;
		
	}
}
