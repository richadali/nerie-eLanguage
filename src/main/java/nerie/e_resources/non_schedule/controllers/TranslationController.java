package nerie.e_resources.non_schedule.controllers;
import nerie.e_resources.non_schedule.dto.TranslationDTO;
import nerie.e_resources.non_schedule.entity.EnglishWordsSentences;
import nerie.e_resources.non_schedule.entity.Language;
import nerie.e_resources.non_schedule.entity.Translation;
import nerie.e_resources.non_schedule.entity.User;
import nerie.e_resources.non_schedule.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/nerie/e-resources/translation")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping("/create")
    public ResponseEntity<String> createTranslation(
            @RequestParam("englishWord") Long englishWordId,
            @RequestParam("language") Long languageId,
            @RequestParam("translatedWord") String translatedWord,
            @RequestParam(value = "audioFile", required = false) MultipartFile audioFile,
            @RequestParam(value = "videoFile", required = false) MultipartFile videoFile,
            @RequestParam("userId") UUID userId) {

        try {
            // Check if a translation already exists for this englishWordId and userId
            Optional<Translation> existingTranslation = translationService.findTranslationByEnglishWordAndUser(englishWordId, userId);

            if (existingTranslation.isPresent()) {
                return new ResponseEntity<>("Duplicate translation: A translation for this word by the same user already exists.", HttpStatus.CONFLICT);
            }

            // Create and save the new translation
            Translation translation = Translation.builder()
                    .englishWordsSentences(EnglishWordsSentences.builder().id(englishWordId).build())
                    .language(Language.builder().id(languageId).build())
                    .Translated_word(translatedWord)
                    .user(User.builder().Id(userId).build())
                    .build();

            translationService.saveTranslation(translation, audioFile, videoFile);
            return new ResponseEntity<>("Translation created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating translation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-by-language/{languageId}")
    public List<Translation> getTranslations(@PathVariable Long languageId) {
        return translationService.getTranslationsByLanguageId(languageId); // Call service to fetch translations
    }

}
