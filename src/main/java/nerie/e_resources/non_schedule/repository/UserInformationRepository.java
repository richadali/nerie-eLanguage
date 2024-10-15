package nerie.e_resources.non_schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import nerie.e_resources.non_schedule.entity.UserInformation;

public interface UserInformationRepository extends JpaRepository<UserInformation,UUID> {

}