package pl.service.event_reminder.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.service.event_reminder.model.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
