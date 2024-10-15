package nerie.e_resources.non_schedule.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nerie.e_resources.non_schedule.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameStartsWithIgnoreCase(String username);

	@Override
	Optional<User> findById(UUID id);

}
