package nerie.e_resources.non_schedule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnglishWordsSentencesDTO {

    @NotNull
    private String EnglishWord;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long subCategoryId;


}
