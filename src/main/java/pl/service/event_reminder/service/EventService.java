package pl.service.event_reminder.service;

import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.web.dto.EventCreationDto;

public interface EventService {
    Event save(EventCreationDto eventCreationDto);
}
