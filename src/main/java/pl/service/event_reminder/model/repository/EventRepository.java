package pl.service.event_reminder.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;

import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long> {

    Set<Event> findAllByUser(User user);

    Set<Event> findAllByIsActiveTrue();
}
