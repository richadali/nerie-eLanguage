package nerie.e_resources.non_schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

public class Tasks {

	@Id
	private String taskId;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{4,15}", message = "Task must have minimum 4 and maximum 7 characters")
	private String taskDescription;

}