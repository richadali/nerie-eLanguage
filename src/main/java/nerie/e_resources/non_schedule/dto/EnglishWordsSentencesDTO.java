package nerie.e_resources.non_schedule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnglishWordsSentencesDTO {

    private String englishWord;

    private Long categoryId;

    private Long subCategoryId;


}
