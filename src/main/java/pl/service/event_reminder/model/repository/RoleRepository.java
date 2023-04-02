package pl.service.event_reminder.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.service.event_reminder.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
