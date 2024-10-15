package nerie.e_resources.non_schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuditTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long auditId;
	private String entityName;
	private String entityId;
	private String action;
	private Long id;

}