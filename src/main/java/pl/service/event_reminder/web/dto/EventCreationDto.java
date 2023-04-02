package pl.service.event_reminder.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EventCreationDto {
    private String eventName;
    private String additionalNote;
    private boolean isActive;
    private String monthGroup;
    private Long userId;
}
