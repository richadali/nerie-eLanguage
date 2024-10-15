package nerie.e_resources.non_schedule.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nerie.e_resources.non_schedule.dto.request.ChangePasswordRequest;
import nerie.e_resources.non_schedule.dto.request.UserRequest;
import nerie.e_resources.non_schedule.dto.request.UserUpdate;
import nerie.e_resources.non_schedule.dto.request.UserUpdateRole;
import nerie.e_resources.non_schedule.dto.response.UserActivitiesResponse;
import nerie.e_resources.non_schedule.dto.response.UserResponse;
import nerie.e_resources.non_schedule.dto.response.UserResponseById;
import nerie.e_resources.non_schedule.entity.AuditTable;
import nerie.e_resources.non_schedule.entity.LoginActivities;
import nerie.e_resources.non_schedule.entity.User;
import nerie.e_resources.non_schedule.entity.UserInformation;
import nerie.e_resources.non_schedule.repository.AuditTableRepository;
import nerie.e_resources.non_schedule.repository.LoginActivitiesRepository;
import nerie.e_resources.non_schedule.repository.UserInformationRepository;
import nerie.e_resources.non_schedule.repository.UserRepository;
import nerie.e_resources.non_schedule.services.AuthenticationService;
import nerie.e_resources.non_schedule.services.UserService;
import nerie.e_resources.non_schedule.utils.RSAUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginActivitiesRepository loginActivitiesRepository;

	@Autowired
	private UserInformationRepository userInfoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private AuditTableRepository auditTable;

	// Get List of Users
	@Override
	public List<UserResponse> getListUsers() {
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(currentUserName);
		List<User> users = userRepository.findAll();

		try {
			if (user.get().getRole().name().equals("ROLE_SUPERADMIN") == false) {

				for (User eachuser : users) {
					if (eachuser.getRole().name().equals("ROLE_SUPERADMIN")) {
						users.remove(eachuser);
					}
				}

			}
		} catch (Exception e) {
			System.out.println("Errror" + e);
		}

		List<UserResponse> userResponse = Arrays.asList(modelMapper.map(users, UserResponse[].class));

		return userResponse;
	}

	// Update User
	@Override
	@Transactional

	public User updateUser(UserRequest user) throws Exception {
		Optional<User> userById = userRepository.findById(user.getId());
		Long loginId = authService.getCurrentUserLoginActivityId();

		UserInformation userInfo = UserInformation.builder().id(user.getId()).office(user.getOffice())
				.officeAddress(user.getOfficeAddress())
				.designation(userById.get().getUserInformation().getDesignation())
				.contactNo(userById.get().getUserInformation().getContactNo()).build();

		userById.ifPresent(currentuser -> {
			currentuser.setFirstName(user.getFirstName());
			currentuser.setMiddleName(user.getMiddleName());
			currentuser.setLastName(user.getLastName());
		});

		userInfoRepository.save(userInfo);

		auditTable.save(AuditTable.builder().id(loginId).entityId(user.getId().toString()).entityName("UserInfo")
				.action("update").build());

		auditTable.save(AuditTable.builder().id(loginId).entityId(user.getId().toString()).entityName("User")
				.action("update").build());

		return userRepository.save(modelMapper.map(userById, User.class));
	}

	// Update role of user
	@Override
	public User updateUserRole(UserUpdateRole reqUpdateRole) {
		Optional<User> user = userRepository.findById(reqUpdateRole.getId());
		user.ifPresent(currentuser -> currentuser.setRole(reqUpdateRole.getRole()));
		return userRepository.save(modelMapper.map(user, User.class));
	}

	// Update active status of user
	@Override
	public User updateUserEnabled(UserUpdate userUpdate) {
		Optional<User> user = userRepository.findById(userUpdate.getId());
		user.ifPresent(currentuser -> currentuser.setEnabled(userUpdate.getUpdateParam()));
		return userRepository.save(modelMapper.map(user, User.class));

	}

	// Update lock status of user
	@Override
	public User lockUnlockUser(UserUpdate userUpdate) {
		Optional<User> user = userRepository.findById(userUpdate.getId());
		user.ifPresent(currentuser -> currentuser.setAccountNonLocked(userUpdate.getUpdateParam()));
		return userRepository.save(modelMapper.map(user, User.class));
	}

	@Override
	public UserResponseById getUserById(UUID id) {
		Optional<User> user = userRepository.findById(id);
		return modelMapper.map(user, UserResponseById.class);
	}

	@Override
	public UserResponseById getUserByUsername() {
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(currentUserName);
		return modelMapper.map(user, UserResponseById.class);
	}

	@Override
	public User changePassword(ChangePasswordRequest passwordChange) throws Exception {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(currentUserName);
		Long loginId = authService.getCurrentUserLoginActivityId();

		String regexpPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,50}$";
		Pattern pwd = Pattern.compile(regexpPassword);

		try {
			final String decryptedOldPassword = RSAUtil.decrypt(passwordChange.getOldPassword());
			final String decryptedNewPassword = RSAUtil.decrypt(passwordChange.getNewPassword());

			Matcher opwd = pwd.matcher(decryptedOldPassword);
			Matcher npwd = pwd.matcher(decryptedNewPassword);

			if (npwd.matches()) {

				if (passwordEncoder.matches(decryptedOldPassword, user.get().getPassword())) {
					user.ifPresent(
							currentuser -> currentuser.setPassword(passwordEncoder.encode(decryptedNewPassword)));
					auditTable.save(AuditTable.builder().id(loginId).entityId(user.get().getId().toString())
							.entityName("User").action("update password").build());
				} else
					throw new BadCredentialsException("Invalid credentials");
			} else
				throw new BadCredentialsException("Invalid credentials");
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid credentials");
		}

		return userRepository.save(modelMapper.map(user, User.class));
	}

	@Override
	public UserResponseById getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsernameStartsWithIgnoreCase(username);
		return modelMapper.map(user, UserResponseById.class);
	}

	@Override
	public List<UserActivitiesResponse> getListUsersActivities() {
		List<UserActivitiesResponse> userActivitiesResponse = new ArrayList<UserActivitiesResponse>();
		List<LoginActivities> loginActivities = loginActivitiesRepository.findAll();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY, HH:mm");

		for (LoginActivities loginActivity : loginActivities) {
			Optional<User> user = userRepository.findById(loginActivity.getUserid());

			if (user != null) {

				UserActivitiesResponse res = UserActivitiesResponse.builder().username(user.get().getUsername())
						.isSuccess(loginActivity.getIsSuccess()).ipAddress(loginActivity.getIpAddress())
						.time(format.format(loginActivity.getTime())).auditTable(loginActivity.getAuditTable()).build();
				userActivitiesResponse.add(res);
			}
		}

		return userActivitiesResponse;
	}

}
