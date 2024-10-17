package nerie.e_resources.non_schedule.controllers;

import nerie.e_resources.non_schedule.entity.MainCategory;
import nerie.e_resources.non_schedule.services.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/main-categories")
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    @Autowired
    public MainCategoryController(MainCategoryService mainCategoryService) {
        this.mainCategoryService = mainCategoryService;
    }

    // GET all categories
    @GetMapping
    public ResponseEntity<List<MainCategory>> getAllMainCategories() {
        List<MainCategory> categories = mainCategoryService.getAllMainCategories();
        return ResponseEntity.ok(categories);
    }

    // GET category by ID
    @GetMapping("/{id}")
    public ResponseEntity<MainCategory> getMainCategoryById(@PathVariable Long id) {
        Optional<MainCategory> category = mainCategoryService.getMainCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}