package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.dto.TranslationDTO;
import nerie.e_resources.non_schedule.entity.Language;
import nerie.e_resources.non_schedule.entity.Translation;
import nerie.e_resources.non_schedule.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TranslationService {

    private final String storagePath = "D:/Development Work/NIC Shillong/nerie-elanguage/audio_video";

    @Autowired
    private TranslationRepository translationRepository;

    public Translation saveTranslation(Translation translation, MultipartFile audioFile, MultipartFile videoFile) throws IOException {
        // Store audio file if present
        if (audioFile != null && !audioFile.isEmpty()) {
            String audioFileName = storeFile(audioFile);
            translation.setAudio(audioFileName);
        }

        // Store video file if present
        if (videoFile != null && !videoFile.isEmpty()) {
            String videoFileName = storeFile(videoFile);
            translation.setVideo(videoFileName);
        }

        return translationRepository.save(translation);
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(storagePath, fileName);
        Files.write(path, file.getBytes());
        return fileName;
    }

    public List<Translation> getTranslationsByLanguageId(Long languageId) {
        return translationRepository.findByLanguageId(languageId); // Fetch translations by language ID
    }
}
