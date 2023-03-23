package pl.service.event_reminder.service;

import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.web.dto.UserRegistration;

public interface UserService {
    User save(UserRegistration userRegistration);
}
