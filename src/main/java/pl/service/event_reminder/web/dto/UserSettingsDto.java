package pl.service.event_reminder.web.dto;

import lombok.*;
import pl.service.event_reminder.model.entity.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSettingsDto {
    private String startDay;
    private String halfDay;
    private String endDay;

    public static UserSettingsDto of(User user) {
        return UserSettingsDto.builder()
                .startDay(String.valueOf(user.getStartDay()))
                .halfDay(String.valueOf(user.getHalfDay()))
                .endDay(String.valueOf(user.getEndDay()))
                .build();
    }
}
