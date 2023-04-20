package pl.service.event_reminder.service;

import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.web.dto.EventCreationDto;

import java.util.Set;

public interface EventService {
    Event save(EventCreationDto eventCreationDto);

    Set<Event> findAllByUser(User user);

    User getCurrentUser();

    Event deactivateEvent(Long id);

    Event activateEvent(Long id);

}
