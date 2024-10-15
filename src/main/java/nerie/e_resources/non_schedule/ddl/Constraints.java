package nerie.e_resources.non_schedule.ddl;
//package nic.meg.signbank.ddl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Constraints {
//	@Autowired
//    private JdbcTemplate jdbcTemplate;
//
//	@EventListener
//	public void alterColumn(ContextRefreshedEvent event) {
//		jdbcTemplate.execute("ALTER TABLE users DROP CONSTRAINT IF EXISTS unique_constraint;");
//        jdbcTemplate.execute("ALTER TABLE users ADD CONSTRAINT unique_constraint UNIQUE (username);");
//	}
//
//}
