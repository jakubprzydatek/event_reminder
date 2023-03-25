package pl.service.event_reminder.model.entity.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.service.event_reminder.model.entity.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapUserToDetailedUser(pl.service.event_reminder.model.entity.User user) {
        return new User(user.getEmail(), user.getPassword(), mapRolesToGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
