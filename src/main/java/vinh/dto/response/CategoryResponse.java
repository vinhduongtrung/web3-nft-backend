package vinh.dto.response;

import vinh.entity.Category;

public class CategoryResponse {
	
	private Long id;
	
	private String name;
	
	private String icon;
	
	private String cover;
	
	
	public CategoryResponse convertEntityToResponse(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.icon = category.getIcon();
		this.cover = category.getCover();
		return this;
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getIcon() {
		return icon;
	}


	public String getCover() {
		return cover;
	}
}
