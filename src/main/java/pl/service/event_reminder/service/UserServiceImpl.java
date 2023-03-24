package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.model.entity.Role;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.repository.UserRepository;
import pl.service.event_reminder.web.dto.UserRegistration;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    @Override
    public User save(UserRegistration userRegistration) {
        User user = User.builder()
                .firstName(userRegistration.getFirstName())
                .lastName(userRegistration.getLastName())
                .email(userRegistration.getEmail())
                .password(userRegistration.getPassword())
                .roles(Set.of(new Role(DEFAULT_USER_ROLE)))
                .build();

        return userRepository.save(user);
    }
}
