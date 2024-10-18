package nerie.e_resources.non_schedule.repository;

import nerie.e_resources.non_schedule.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    // Method to find languages by state
    List<Language> findByStateId_Id(Long stateId);
}
