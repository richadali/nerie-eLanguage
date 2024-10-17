package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.entity.MainCategory;
import nerie.e_resources.non_schedule.repository.MainCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    @Autowired
    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }

    // Get all categories
    public List<MainCategory> getAllMainCategories() {
        return mainCategoryRepository.findAll();
    }

    // Get a category by ID
    public Optional<MainCategory> getMainCategoryById(Long id) {
        return mainCategoryRepository.findById(id);
    }
}