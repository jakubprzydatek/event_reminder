package pl.service.event_reminder.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventException extends RuntimeException{

    public EventException(String message) {
        super(message);
    }
}
