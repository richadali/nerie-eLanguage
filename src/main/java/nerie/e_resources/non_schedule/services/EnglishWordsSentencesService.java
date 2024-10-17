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

import java.util.List;

@Service
public class EnglishWordsSentencesService {

    @Autowired
    private EnglishWordsSentencesRepository englishWordsSentencesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public EnglishWordsSentences createEnglishWordSentence(EnglishWordsSentencesDTO dto) {
        // Fetch the category, ensuring categoryId is not null
        if (dto.getCategoryId() == null) {
            throw new RuntimeException("Category ID must not be null");
        }

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Handle the potential null for subCategoryId
        SubCategory subCategory = null;
        if (dto.getSubCategoryId() != null) {
            subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                    .orElseThrow(() -> new RuntimeException("Sub Category not found"));
        }

        // Ensure EnglishWord is set correctly
        EnglishWordsSentences englishWordsSentences = EnglishWordsSentences.builder()
                .EnglishWord(dto.getEnglishWord())
                .category(category)
                .subCategory(subCategory) // This can be null
                .build();

        // Log to see the constructed entity
        System.out.println("EnglishWordSentences entity to save: " + englishWordsSentences);

        return englishWordsSentencesRepository.save(englishWordsSentences);
    }



    public List<EnglishWordsSentences> getAllEnglishWordsSentences() {
        return englishWordsSentencesRepository.findAll();
    }

}
