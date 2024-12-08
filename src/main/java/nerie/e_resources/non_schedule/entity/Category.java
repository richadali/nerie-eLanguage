package nerie.e_resources.non_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 250, message = "Category Name should have maximum 250 characters")
	private String categoryName;

	@ManyToOne
	@JoinColumn(name="main_category_id" , referencedColumnName = "id")
	private MainCategory mainCategory;

}