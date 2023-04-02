package pl.service.event_reminder.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.web.dto.UserRegistration;

public interface UserService extends UserDetailsService {
    User save(UserRegistration userRegistration);
}
