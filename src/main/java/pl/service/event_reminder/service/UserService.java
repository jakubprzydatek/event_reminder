package pl.service.event_reminder.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.web.dto.UserRegistrationDto;
import pl.service.event_reminder.web.dto.UserSettingsDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);

    User findById(Long id);

    User findByEmail(String email);

    User getCurrentUser();

    User updateUserSettings(UserSettingsDto userSettingsDto);
}
