package nerie.e_resources.non_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnglishWordsSentences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String EnglishWord;

    @ManyToOne
    @JoinColumn(name="category_id" , referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="sub_category_id" , referencedColumnName = "id")
    private SubCategory subCategory;


}
