package nerie.e_resources.non_schedule.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

public class PageRequest {

	@NotNull
	@Min(0)
	@Max(100)
	private Integer pageNo;

	@NotNull
	@Min(20)
	@Max(80)
	private Integer pageSize;

}