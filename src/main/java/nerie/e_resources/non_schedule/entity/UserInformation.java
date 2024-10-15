package nerie.e_resources.non_schedule.entity;

import java.util.UUID;

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

public class UserInformation {

	@Id
	private UUID id;

	@NotNull
	@Size(max = 250, message = "Office should have maximum 250 characters")
	private String office;

	@NotNull
	@Size(max = 250, message = "Office address should have maximum 250 characters")
	private String officeAddress;

	@NotNull
	@Size(max = 100, message = "Designation should have maximum 100 characters")
	private String designation;

	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be positive integer and 10 digits")
	private String contactNo;

}