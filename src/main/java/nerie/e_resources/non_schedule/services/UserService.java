package nerie.e_resources.non_schedule.services;

import java.util.List;
import java.util.UUID;

import nerie.e_resources.non_schedule.dto.request.ChangePasswordRequest;
import nerie.e_resources.non_schedule.dto.request.UserRequest;
import nerie.e_resources.non_schedule.dto.request.UserUpdate;
import nerie.e_resources.non_schedule.dto.request.UserUpdateRole;
import nerie.e_resources.non_schedule.dto.response.UserActivitiesResponse;
import nerie.e_resources.non_schedule.dto.response.UserResponse;
import nerie.e_resources.non_schedule.dto.response.UserResponseById;
import nerie.e_resources.non_schedule.entity.User;

public interface UserService {

	List<UserResponse> getListUsers();

	List<UserActivitiesResponse> getListUsersActivities();

	UserResponseById getUserById(UUID id);

	UserResponseById getUserByUsername();

	UserResponseById getUserByUsername(String username);

	User updateUser(UserRequest user) throws Exception;

	User updateUserRole(UserUpdateRole reqUpdateRole);

	User updateUserEnabled(UserUpdate userUpdate);

	User lockUnlockUser(UserUpdate userUpdate);

	User changePassword(ChangePasswordRequest passwordChange) throws Exception;

}