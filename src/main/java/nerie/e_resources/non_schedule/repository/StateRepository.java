package nerie.e_resources.non_schedule.repository;

import nerie.e_resources.non_schedule.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
