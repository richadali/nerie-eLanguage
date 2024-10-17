package nerie.e_resources.non_schedule.controllers;

import nerie.e_resources.non_schedule.dto.EnglishWordsSentencesDTO;
import nerie.e_resources.non_schedule.entity.EnglishWordsSentences;
import nerie.e_resources.non_schedule.services.EnglishWordsSentencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/nerie/e-resources/english-words")
public class EnglishWordsSentencesController {

    @Autowired
    private EnglishWordsSentencesService englishWordsSentencesService;

    @PostMapping("/create")
    public ResponseEntity<EnglishWordsSentences> createEnglishWordSentence(@Valid @RequestBody EnglishWordsSentencesDTO dto) {

        System.out.println("Create.............");
        EnglishWordsSentences createdEnglishWordSentence = englishWordsSentencesService.createEnglishWordSentence(dto);
        return new ResponseEntity<>(createdEnglishWordSentence, HttpStatus.OK);
//        return ResponseEntity.ok(createdEnglishWordSentence);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnglishWordsSentences>> getAllEnglishWordsSentences() {
        List<EnglishWordsSentences> wordsSentences = englishWordsSentencesService.getAllEnglishWordsSentences();
        return new ResponseEntity<>(wordsSentences, HttpStatus.OK);
    }
}
