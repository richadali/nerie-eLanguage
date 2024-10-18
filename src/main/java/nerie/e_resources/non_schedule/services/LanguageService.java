package nerie.e_resources.non_schedule.services;

import nerie.e_resources.non_schedule.entity.Language;
import nerie.e_resources.non_schedule.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    // Fetch languages based on the selected state
    public List<Language> getLanguagesByState(Long stateId) {
        return languageRepository.findByStateId_Id(stateId);
    }
}
