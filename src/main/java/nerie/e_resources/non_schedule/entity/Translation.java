package nerie.e_resources.non_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="eng_word_id" , referencedColumnName = "id")
    private EnglishWordsSentences englishWordsSentences;

    @ManyToOne
    @JoinColumn(name="language_id" , referencedColumnName = "id")
    private Language language;

    @NotNull
    private String Translated_word;

    private String video;

    private String audio;

    @ManyToOne
    @JoinColumn(name="user_id" , referencedColumnName = "Id")
    private User user;

    private Date CreatedAt;

    @PrePersist
    public void onCreate() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        this.setCreatedAt(date);
    }

}
