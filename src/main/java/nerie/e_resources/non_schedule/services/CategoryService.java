package nerie.e_resources.non_schedule.services;

import java.util.List;
import nerie.e_resources.non_schedule.entity.Category;
import nerie.e_resources.non_schedule.entity.MainCategory;
import nerie.e_resources.non_schedule.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategoriesByMainCategory(MainCategory mainCategory) {
        return categoryRepository.findByMainCategory(mainCategory);
    }
}
