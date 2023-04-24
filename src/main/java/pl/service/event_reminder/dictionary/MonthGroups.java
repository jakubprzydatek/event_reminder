package pl.service.event_reminder.dictionary;

import pl.service.event_reminder.exception.EventException;

public enum MonthGroups {
    START("Start"), HALF("Half"), END("End");

    public final String value;

    private MonthGroups(String value) {
        this.value = value;
    }

    public static MonthGroups fromString(String value) {
        return switch (value) {
            case "Start" -> MonthGroups.START;
            case "Half" -> MonthGroups.HALF;
            case "End" -> MonthGroups.END;
            default -> throw new EventException("Cannot find MonthGroup with such value");
        };
    }
}
