package nerie.e_resources.non_schedule.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserUpdate {

	@NotNull
	private UUID id;

	@NotNull
	private Boolean updateParam;
}