package nerie.e_resources.non_schedule.repository;

import nerie.e_resources.non_schedule.dto.TranslationDTO;
import nerie.e_resources.non_schedule.entity.Language;
import nerie.e_resources.non_schedule.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    List<Translation> findByLanguageId(Long languageId);

    Optional<Translation> findByEnglishWordsSentences_IdAndUser_Id(Long englishWordId, UUID userId);
}
