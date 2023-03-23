package pl.service.event_reminder.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRegistration {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
