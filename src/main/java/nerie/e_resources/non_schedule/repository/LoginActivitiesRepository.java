package nerie.e_resources.non_schedule.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nerie.e_resources.non_schedule.entity.LoginActivities;

@Repository
public interface LoginActivitiesRepository extends JpaRepository<LoginActivities, Long> {
	@Query(value = "SELECT * FROM login_activities t WHERE userid=:id ORDER BY t.id DESC LIMIT 1", nativeQuery = true)
	List<LoginActivities> findByUserid(@Param("id") UUID id);

}
