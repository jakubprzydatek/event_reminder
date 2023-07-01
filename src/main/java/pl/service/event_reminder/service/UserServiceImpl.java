package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.authentication.IAuthenticationFacade;
import pl.service.event_reminder.config.PasswordEncoder;
import pl.service.event_reminder.exception.EventException;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.entity.mapper.UserMapper;
import pl.service.event_reminder.model.repository.UserRepository;
import pl.service.event_reminder.validator.UserValidator;
import pl.service.event_reminder.web.dto.UserRegistrationDto;
import pl.service.event_reminder.web.dto.UserSettingsDto;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder bCryptPassword;
    private final IAuthenticationFacade authenticationFacade;
    private final UserValidator userValidator;

    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = User.builder()
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .email(userRegistrationDto.getEmail())
                .password(bCryptPassword.encode(userRegistrationDto.getPassword()))
                .roles(Set.of(roleService.getRoleByName(DEFAULT_USER_ROLE)))
                .startDay(1)
                .halfDay(12)
                .endDay(23)
                .build();

        log.info("Creating new user with email: {}", userRegistrationDto.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }else {
            throw new EventException("Cannot find user with such ID");
        }
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();

            return findByEmail(userName);
        }else {
            throw new AuthenticationServiceException("Błąd autoryzacji");
        }
    }

    @Override
    public User updateUserSettings(UserSettingsDto userSettingsDto) {
        userValidator.validateUserSettings(userSettingsDto);

        User user = getCurrentUser();
        user.setStartDay(Integer.parseInt(userSettingsDto.getStartDay()));
        user.setHalfDay(Integer.parseInt(userSettingsDto.getHalfDay()));
        user.setEndDay(Integer.parseInt(userSettingsDto.getEndDay()));

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
