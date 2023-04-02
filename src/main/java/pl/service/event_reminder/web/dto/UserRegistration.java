package pl.service.event_reminder.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistration {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
