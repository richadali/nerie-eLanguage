package nerie.e_resources.non_schedule.repository;

import nerie.e_resources.non_schedule.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
