package nerie.e_resources.non_schedule.controllers;

import nerie.e_resources.non_schedule.entity.SubCategory;
import nerie.e_resources.non_schedule.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sub-categories")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    // GET API to fetch subcategories by Category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategory(@PathVariable Long categoryId) {
        List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(subCategories);
    }
}
