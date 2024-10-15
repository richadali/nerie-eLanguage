package nerie.e_resources.non_schedule.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import nerie.e_resources.non_schedule.config.ApplicationConfig;
import nerie.e_resources.non_schedule.entity.LoginActivities;
import nerie.e_resources.non_schedule.entity.User;
import nerie.e_resources.non_schedule.repository.LoginActivitiesRepository;
import nerie.e_resources.non_schedule.repository.UserRepository;
import nerie.e_resources.non_schedule.services.PasswordService;
import nerie.e_resources.non_schedule.utils.RSAUtil;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LoginActivitiesRepository loginActivities;

	@Autowired
	private HttpServletRequest request;

	final ApplicationConfig appConfig = new ApplicationConfig(repository);

	@Override
	public UserDetails isValidUser(String username, String password) {

		String regexpPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,50}$";
		String regexpUsername = "^[a-zA-Z0-9]{6,20}$";

		Pattern pwd = Pattern.compile(regexpPassword);
		Pattern un = Pattern.compile(regexpUsername);

		String decryptedPassword;
		User userMapped = null;

		try {
			decryptedPassword = RSAUtil.decrypt(password);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid credentials");
		}

		Matcher mpwd = pwd.matcher(decryptedPassword);
		Matcher mun = un.matcher(username);

		if (mpwd.matches() && mun.matches()) {

			Optional<User> user = repository.findByUsername(username);

			if (!appConfig.passwordEncoder().matches(decryptedPassword, user.get().getPassword())) {
				LoginActivities login_activity = LoginActivities.builder().userid(user.get().getId())
						.ipAddress(request.getRemoteAddr()).isSuccess(false).build();
				loginActivities.save(login_activity);
				throw new BadCredentialsException("Invalid credentials");
			}
			LoginActivities login_activity = LoginActivities.builder().userid(user.get().getId())
					.ipAddress(request.getRemoteAddr()).isSuccess(true).build();
			loginActivities.save(login_activity);

			userMapped = modelMapper.map(user, User.class);
		} else {
			throw new BadCredentialsException("Invalid credentials");
		}
		return userMapped;
	}

}