package pl.service.event_reminder.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.service.event_reminder.model.entity.MonthGroup;

public interface MonthGroupRepository extends JpaRepository<MonthGroup, Long> {
    MonthGroup findByMonthGroup(String monthGroup);
}
