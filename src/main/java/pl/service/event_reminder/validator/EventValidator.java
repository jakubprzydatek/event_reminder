package pl.service.event_reminder.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.service.event_reminder.exception.EventValidationException;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.web.dto.EventCreationDto;

@Component
public class EventValidator {
    public void validateEvent(EventCreationDto event) {
        if(!StringUtils.hasLength(event.getEventName())) throw new EventValidationException("Nazwa wydarzenia nie może być pusta", event.getId(), "CREATION");
    }
}
