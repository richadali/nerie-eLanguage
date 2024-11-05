
package nerie.e_resources.non_schedule.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import nerie.e_resources.non_schedule.dto.request.ChangePasswordRequest;
import nerie.e_resources.non_schedule.dto.request.UserRequest;
import nerie.e_resources.non_schedule.dto.request.UserUpdate;
import nerie.e_resources.non_schedule.dto.request.UserUpdateRole;
import nerie.e_resources.non_schedule.dto.response.UserActivitiesResponse;
import nerie.e_resources.non_schedule.dto.response.UserResponse;
import nerie.e_resources.non_schedule.dto.response.UserResponseById;
import nerie.e_resources.non_schedule.services.UserService;

@Controller
@RequestMapping("/nerie/e-resources/")

public class UserController {

	@Autowired
	private UserService userService;

	// Create User Page
	@GetMapping("/create-user")

	public String createUser() {

		return "create-user";
	}

	@GetMapping("/create-word")
	public String createWord() {
		return "create-word";
	}

	@GetMapping(value = "/translate-word-sentences")
	public String translate() {
		return "translate-word";
	}

	@GetMapping(value = "/translated-words")
	public String translatedWords() {
		return "translated-words";
	}

	// Get List of users Page
	@GetMapping("/get-list-users")

	public String getListUsers() {

		return "get-list-users";
	}

	// Get List of users Activities Page
	@GetMapping("/get-list-login-activities")

	public String getListLoginActivities() {

		return "get-list-login-activities";
	}

	// Get User Profile
	@GetMapping(value = "/user-profile")
	public String user_profile() {
		return "user-profile";
	}

	// Change Password
	@GetMapping(value = "/change-password")
	public String changePassword() {
		return "change-password";
	}

	// Get List of users
	@PostMapping("/get-list-users-res")

	public @ResponseBody ResponseEntity<String> getListUsersRes(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<UserResponse> users = userService.getListUsers();
		System.out.println(users);

		for (UserResponse user : users) {
			user.setUserInformation(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body("/*" + new ObjectMapper().writeValueAsString(users) + "*/");
	}

	// Get List of users Activities
	@PostMapping("/get-list-login-activities-res")

	public @ResponseBody ResponseEntity<String> getListUersActivitiesRes(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<UserActivitiesResponse> users = userService.getListUsersActivities();

		return ResponseEntity.status(HttpStatus.OK).body("/*" + new ObjectMapper().writeValueAsString(users) + "*/");
	}

	// Get User (All details)
	@PostMapping("/get-user-by-id")

	public @ResponseBody ResponseEntity<String> getUserById(@RequestBody Map<String, String> userId,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		UserResponseById user = userService.getUserById(UUID.fromString(userId.get("id")));
		return ResponseEntity.status(HttpStatus.OK).body("/*" + new ObjectMapper().writeValueAsString(user) + "*/");
	}

	// Get User By Username
	@PostMapping("/get-user-by-username")

	public @ResponseBody ResponseEntity<String> getUserByUsername() throws IOException {
		UserResponseById user = userService.getUserByUsername();
		System.out.println(user.getFirstName());
		return ResponseEntity.status(HttpStatus.OK).body("/*" + new ObjectMapper().writeValueAsString(user) + "*/");
	}

	// Update User
	@PostMapping(value = "/update-user")

	public ResponseEntity<String> updateUser(@RequestBody UserRequest user) throws Exception {

		if (userService.updateUser(user) != null)
			return ResponseEntity.status(HttpStatus.OK).body("Updated Succesfully!");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
	}

	// Update Role
	@PostMapping(value = "/update-role")

	public ResponseEntity<String> UpdateUserRole(@RequestBody UserUpdateRole reqUpdateRole) {

		if (userService.updateUserRole(reqUpdateRole) != null)
			return ResponseEntity.status(HttpStatus.OK).body("Updated Succesfully!");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
	}

	// Enable User
	@PostMapping(value = "/enable-user")

	public ResponseEntity<String> enableUser(@RequestBody UserUpdate reqUpdateEnabled) {

		if (userService.updateUserEnabled(reqUpdateEnabled) != null)
			return ResponseEntity.status(HttpStatus.OK).body("Updated Succesfully!");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
	}

	// Lock/Unlock User
	@PostMapping(value = "/lock-unlock-user")

	public ResponseEntity<String> lockUnlockUser(@RequestBody UserUpdate reqUpdateEnabled) {

		if (userService.updateUserEnabled(reqUpdateEnabled) != null)
			return ResponseEntity.status(HttpStatus.OK).body("Updated Succesfully!");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
	}

	// Change Password User
	@PostMapping(value = "/change-password")

	public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest passwordChange)
			throws Exception {

		if (userService.changePassword(passwordChange) != null)
			return ResponseEntity.status(HttpStatus.OK).body("Updated Succesfully!");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
	}

	// Search a user
	@PostMapping(value = "/search-user")

	public @ResponseBody ResponseEntity<String> searchUser(@RequestBody Map<String, String> username,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		UserResponseById user = userService.getUserByUsername(username.get("username"));
		return ResponseEntity.status(HttpStatus.OK).body("/*" + new ObjectMapper().writeValueAsString(user) + "*/");
	}

}
