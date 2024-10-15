package nerie.e_resources.non_schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerie.e_resources.non_schedule.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "Minimum 6 characters and maximum 20 characters. Special characters are not allowed for username.")
	private String username;

	@NotNull
	@Pattern(regexp = "^(?:[a-zA-Z0-9+\\/]{4})*(?:|(?:[a-zA-Z0-9+\\/]{3}=)|(?:[a-zA-Z0-9+\\/]{2}==)|(?:[a-zA-Z0-9+\\/]{1}===))$", message = "Invalid password")
	private String password;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,50}$", message = "Firstname should have maximum 50 characters. No special characters allowed.")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]{0,50}$", message = "Middlename should have maximum 50 characters. No special characters allowed.")
	private String middleName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,50}$", message = "Lastname should have maximum 50 characters. No special characters allowed.")
	private String lastName;

	@NotNull
	@JsonProperty("isSuperuser")
	private Boolean isSuperuser;

	@NotNull
	@JsonProperty("isEnabled")
	private Boolean isEnabled;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ,-]{1,250}$", message = "Office should have maximum 250 characters.")
	private String office;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ,-]{1,250}$", message = "Office Address should have maximum 250 characters.")
	private String officeAddress;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ,-]{1,100}$", message = "Designation should have maximum 100 characters.")
	private String designation;

	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be positive integer and 10 digits")
	private String contactNo;

	@NotNull
	private Role role;

}