package nerie.e_resources.non_schedule.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface PasswordService {

	public UserDetails isValidUser(String username, String password);

}