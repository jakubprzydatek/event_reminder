package pl.service.event_reminder.dictionary;

public enum MonthGroups {
    START("Start"), HALF("Half"), END("End");

    public final String value;

    private MonthGroups(String value) {
        this.value = value;
    }
}
