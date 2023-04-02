package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.config.PasswordEncoder;
import pl.service.event_reminder.model.entity.Role;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.entity.mapper.UserMapper;
import pl.service.event_reminder.model.repository.UserRepository;
import pl.service.event_reminder.web.dto.UserRegistration;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder bCryptPassword;
    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    @Override
    public User save(UserRegistration userRegistration) {
        User user = User.builder()
                .firstName(userRegistration.getFirstName())
                .lastName(userRegistration.getLastName())
                .email(userRegistration.getEmail())
                .password(bCryptPassword.encode(userRegistration.getPassword()))
                .roles(Set.of(roleService.getRoleByName(DEFAULT_USER_ROLE)))
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("Cannot find user with given email address");
        }

        return UserMapper.mapUserToDetailedUser(user);
    }

}
