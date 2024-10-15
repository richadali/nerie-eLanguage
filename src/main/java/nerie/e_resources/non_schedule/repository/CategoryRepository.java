package nerie.e_resources.non_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nerie.e_resources.non_schedule.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	@Query(value = "SELECT MAX(category_code) FROM Category", nativeQuery = true)
	String getMaxCategoryCode();

}