package nerie.e_resources.non_schedule.controllers;

import nerie.e_resources.non_schedule.entity.Language;
import nerie.e_resources.non_schedule.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nerie/e-resources/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    // API to get languages based on the selected state
    @GetMapping("/by-state/{stateId}")
    public List<Language> getLanguagesByState(@PathVariable Long stateId) {
        return languageService.getLanguagesByState(stateId);
    }
}
