package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.entity.Translation;
import nerie.e_resources.non_schedule.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    @Value("${file.storage.location}")
    private String storagePath;

    @Value("${file.access.url}")
    private String fileAccessUrl; // URL path to access files

    @Autowired
    private TranslationRepository translationRepository;

    public Optional<Translation> findTranslationByEnglishWordAndUser(Long englishWordId, UUID userId) {
        return translationRepository.findByEnglishWordsSentences_IdAndUser_Id(englishWordId, userId);
    }

    public Translation saveTranslation(Translation translation, MultipartFile audioFile, MultipartFile videoFile) throws IOException {
        // Store audio file if present
        if (audioFile != null && !audioFile.isEmpty()) {
            String audioFileName = storeFile(audioFile);
            translation.setAudio(audioFileName);  // Store only filename in DB
        }

        // Store video file if present
        if (videoFile != null && !videoFile.isEmpty()) {
            String videoFileName = storeFile(videoFile);
            translation.setVideo(videoFileName);  // Store only filename in DB
        }

        return translationRepository.save(translation);
    }

    private String storeFile(MultipartFile file) throws IOException {
        // Generate a unique file name to avoid conflicts
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

        Path path = Paths.get(storagePath).resolve(uniqueFileName);

        // Ensure the storage directory exists
        Files.createDirectories(path.getParent());

        // Write file to the specified path
        Files.write(path, file.getBytes());

        return uniqueFileName;
    }

    public List<Translation> getTranslationsByLanguageId(Long languageId) {
        // Fetch translations by language ID
        List<Translation> translations = translationRepository.findByLanguageId(languageId);

        // Add URL paths for each file in the translation objects
        return translations.stream().map(this::addFileUrls).collect(Collectors.toList());
    }

    private Translation addFileUrls(Translation translation) {
        if (translation.getAudio() != null) {
            translation.setAudio(getFileUrl(translation.getAudio())); // URL path for audio
        }
        if (translation.getVideo() != null) {
            translation.setVideo(getFileUrl(translation.getVideo())); // URL path for video
        }
        return translation;
    }

    private String getFileUrl(String fileName) {
        // Return the URL path to access the file instead of the full filesystem path
        return fileAccessUrl + "/" + fileName;
    }
}
