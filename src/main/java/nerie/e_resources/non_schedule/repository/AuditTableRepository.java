package nerie.e_resources.non_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nerie.e_resources.non_schedule.entity.AuditTable;

@Repository
public interface AuditTableRepository extends JpaRepository<AuditTable, Long> {

}
