package nerie.e_resources.non_schedule.repository;

import nerie.e_resources.non_schedule.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nerie.e_resources.non_schedule.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByMainCategory(MainCategory mainCategory);
}
