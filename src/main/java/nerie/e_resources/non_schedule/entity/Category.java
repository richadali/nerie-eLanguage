package nerie.e_resources.non_schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Category {

	@Id
	@NotNull
	@Pattern(regexp = "^CAT[0-9]{4}$", message = "Category code must have 7 chracters")
	private String categoryCode;

	@NotNull
	@Size(max = 250, message = "Category Description should have maximum 250 characters")
	private String categoryDescription;

}