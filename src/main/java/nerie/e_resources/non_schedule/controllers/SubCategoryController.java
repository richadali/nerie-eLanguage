package nerie.e_resources.non_schedule.controller;

import nerie.e_resources.non_schedule.entity.SubCategory;
import nerie.e_resources.non_schedule.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubCategory>> getSubCategories(@RequestParam Long categoryId) {
        List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(subCategories);
    }
}
