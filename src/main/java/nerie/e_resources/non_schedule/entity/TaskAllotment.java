package nerie.e_resources.non_schedule.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

public class TaskAllotment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private UUID userId;

	@OneToOne
	@JoinColumn(name = "taskId", referencedColumnName = "taskId")
	private Tasks tasks;
}