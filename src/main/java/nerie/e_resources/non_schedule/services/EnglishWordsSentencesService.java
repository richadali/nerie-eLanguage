package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.dto.EnglishWordsSentencesDTO;
import nerie.e_resources.non_schedule.entity.Category;
import nerie.e_resources.non_schedule.entity.EnglishWordsSentences;
import nerie.e_resources.non_schedule.entity.SubCategory;
import nerie.e_resources.non_schedule.repository.CategoryRepository;
import nerie.e_resources.non_schedule.repository.EnglishWordsSentencesRepository;
import nerie.e_resources.non_schedule.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnglishWordsSentencesService {

    @Autowired
    private EnglishWordsSentencesRepository englishWordsSentencesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public EnglishWordsSentences createEnglishWordSentence(EnglishWordsSentencesDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        SubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("Sub Category not found"));

        EnglishWordsSentences englishWordsSentences = EnglishWordsSentences.builder()
                .EnglishWord(dto.getEnglishWord())
                .category(category)
                .subCategory(subCategory)
                .build();

        return englishWordsSentencesRepository.save(englishWordsSentences);
    }
}
