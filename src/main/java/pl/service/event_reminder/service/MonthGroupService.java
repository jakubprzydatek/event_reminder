package pl.service.event_reminder.service;

import pl.service.event_reminder.model.entity.MonthGroup;

public interface MonthGroupService {
    MonthGroup findByName(String monthGroupName);
}
