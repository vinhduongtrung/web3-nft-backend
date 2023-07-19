package vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinh.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
