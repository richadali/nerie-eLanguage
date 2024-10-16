//package nerie.e_resources.non_schedule.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import nerie.e_resources.non_schedule.entity.Category;
//import nerie.e_resources.non_schedule.repository.CategoryRepository;
//import nerie.e_resources.non_schedule.services.WordService;
//
//@Service
//public class WordServiceImpl implements WordService {
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	// Add new Category
//	@Override
//	public void addCategory(String category) {
//		String categoryCode = "CAT" + String.format("%04d",
//				Integer.parseInt(categoryRepository.getMaxCategoryCode().replace("CAT", "")) + 1);
//
//		System.out.println(categoryCode);
//
//		Category newCategory = Category.builder().categoryCode(categoryCode).categoryDescription(category).build();
//		categoryRepository.save(newCategory);
//
//	}
//
//	// Get List of Categories
//	@Override
//	public List<Category> getListCategories() {
//		return categoryRepository.findAll();
//	}
//
//	@Override
//	public void editCategory(Category category) {
//		categoryRepository.save(category);
//	}
//
//}
