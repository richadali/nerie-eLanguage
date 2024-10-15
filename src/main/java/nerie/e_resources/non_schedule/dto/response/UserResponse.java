package nerie.e_resources.non_schedule.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nerie.e_resources.non_schedule.entity.UserInformation;
import nerie.e_resources.non_schedule.enums.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserResponse {

	private UUID Id;
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean enabled;
	private String dateJoined;
	private Role role;
	private UserInformation userInformation;
}