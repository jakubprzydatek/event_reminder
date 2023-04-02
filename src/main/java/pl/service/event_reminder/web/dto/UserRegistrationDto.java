package pl.service.event_reminder.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
