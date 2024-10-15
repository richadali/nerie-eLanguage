package nerie.e_resources.non_schedule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nerie.e_resources.non_schedule.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
	List<Link> findByWordId(String wordId);

	void deleteAllByWordId(String wordId);

}