package pl.service.event_reminder;

public class EventValidationException extends RuntimeException{
    public EventValidationException(String message) {
        super(message);
    }
}
