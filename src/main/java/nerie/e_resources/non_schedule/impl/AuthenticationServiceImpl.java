package nerie.e_resources.non_schedule.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nerie.e_resources.non_schedule.dto.request.RegisterRequest;
import nerie.e_resources.non_schedule.entity.AuditTable;
import nerie.e_resources.non_schedule.entity.LoginActivities;
import nerie.e_resources.non_schedule.entity.User;
import nerie.e_resources.non_schedule.entity.UserInformation;
import nerie.e_resources.non_schedule.repository.AuditTableRepository;
import nerie.e_resources.non_schedule.repository.LoginActivitiesRepository;
import nerie.e_resources.non_schedule.repository.UserInformationRepository;
import nerie.e_resources.non_schedule.repository.UserRepository;
import nerie.e_resources.non_schedule.services.AuthenticationService;
import nerie.e_resources.non_schedule.utils.RSAUtil;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private UserInformationRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuditTableRepository auditTable;

	@Autowired
	private LoginActivitiesRepository loginActivitiesRepository;

	@Transactional
	@Override
	public void register(RegisterRequest request) throws Exception {

		String decryptedPassword;
		String regexpPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,50}$";
		Pattern pwd = Pattern.compile(regexpPassword);

		try {
			decryptedPassword = RSAUtil.decrypt(request.getPassword());
		} catch (Exception e) {
			throw new Exception("Invalid password");
		}

		Matcher mpwd = pwd.matcher(decryptedPassword);
		if (mpwd.matches()) {

			User user = User.builder().username(request.getUsername())
					.password(passwordEncoder.encode(decryptedPassword)).firstName(request.getUsername())
					.middleName(request.getMiddleName()).lastName(request.getLastName())
					.isSuperuser(request.getIsSuperuser()).enabled(request.getIsEnabled()).accountNonExpired(true)
					.accountNonLocked(true).credentialsNonExpired(true).role(request.getRole()).build();

			// Save user instance
			repository.save(user);

			UUID id = repository.findByUsername(request.getUsername()).get().getId();
			UserInformation userInformation = UserInformation.builder().id(id).office(request.getOffice())
					.officeAddress(request.getOfficeAddress()).designation(request.getDesignation())
					.contactNo(request.getContactNo()).build();

			userInfoRepository.save(userInformation);

			Long loginId = getCurrentUserLoginActivityId();

			auditTable.save(
					AuditTable.builder().id(loginId).entityId(id.toString()).entityName("Users").action("add").build());

			auditTable.save(AuditTable.builder().id(loginId).entityId(id.toString()).entityName("UserInfo")
					.action("add").build());

		} else
			throw new Exception(
					"Username special characters are  not allowed. Minimum 6 and maximum 20 characters. Password minimum 8 and maximum 50 charcters. It should contain atleast 1 Capital letter , 1 small letter, one number and 1 special character.");

	}

	@Override
	public Long getCurrentUserLoginActivityId() throws Exception {
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> currentUser = repository.findByUsername(currentUserName);
		List<LoginActivities> loginActivities = loginActivitiesRepository.findByUserid(currentUser.get().getId());
		return loginActivities.get(0).getId();
	}

}