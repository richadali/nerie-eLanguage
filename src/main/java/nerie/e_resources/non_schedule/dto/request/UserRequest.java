package nerie.e_resources.non_schedule.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

public class UserRequest {

	private UUID id;

	@NotNull
	@Size(min = 6, max = 20, message = "Username should be atleast 6 characters and maximum 20 characters")
	private String username;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,50}$", message = "Firstname should have maximum 50 characters. No special characters allowed.")
	private String firstName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{0,50}$", message = "Middlename should have maximum 50 characters. No special characters allowed.")
	private String middleName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,50}$", message = "Lastname should have maximum 50 characters. No special characters allowed.")
	private String lastName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ,-]{1,250}$", message = "Office should have maximum 250 characters.")
	private String office;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ,-]{1,250}$", message = "Office Address should have maximum 250 characters.")
	private String officeAddress;

}