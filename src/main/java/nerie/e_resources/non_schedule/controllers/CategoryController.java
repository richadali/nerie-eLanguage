package nerie.e_resources.non_schedule.controllers;

import nerie.e_resources.non_schedule.entity.Category;
import nerie.e_resources.non_schedule.entity.MainCategory;
import nerie.e_resources.non_schedule.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET API to fetch categories by MainCategory
    @GetMapping("/main-category/{mainCategoryId}")
    public ResponseEntity<List<Category>> getCategoriesByMainCategory(@PathVariable Long mainCategoryId) {
        MainCategory mainCategory = new MainCategory();
        mainCategory.setId(mainCategoryId);
        List<Category> categories = categoryService.getCategoriesByMainCategory(mainCategory);
        return ResponseEntity.ok(categories);
    }
}
