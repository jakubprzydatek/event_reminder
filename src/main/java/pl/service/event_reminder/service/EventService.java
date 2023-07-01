package pl.service.event_reminder.service;

import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.web.dto.EventCreationDto;

import java.util.Set;

public interface EventService {
    Event save(EventCreationDto eventCreationDto);

    Event edit(EventCreationDto eventCreationDto);

    Event findById(Long id);

    Set<Event> findAllByUser(User user);

    Event deactivateEvent(Long id);

    Event activateEvent(Long id);

    void setNotificationDateAfterEmailSent(Set<Event> events);

}
