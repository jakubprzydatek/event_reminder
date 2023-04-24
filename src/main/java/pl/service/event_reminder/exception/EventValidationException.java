package pl.service.event_reminder.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventValidationException extends RuntimeException{

    private Long eventId;
    private String operationName;

    public EventValidationException(String message) {
        super(message);
    }

    public EventValidationException(String message, Long eventId, String operationName) {
        super(message);
        this.eventId = eventId;
        this.operationName = operationName;
    }
}
