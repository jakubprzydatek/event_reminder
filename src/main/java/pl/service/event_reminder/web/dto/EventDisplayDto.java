package pl.service.event_reminder.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.service.event_reminder.model.entity.Event;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDisplayDto {
    private Long id;
    private String eventName;
    private String additionalNote;
    private String monthGroup;
    private boolean isActive;

    public static EventDisplayDto map(Event event) {
        return builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .additionalNote(event.getAdditionalNote())
                .monthGroup(event.getMonthGroup().getMonthGroup())
                .isActive(event.isActive())
                .build();
    }

    public static List<EventDisplayDto> mapEventsToDto(Set<Event> events) {
        return events.stream()
                .map(EventDisplayDto::map)
                .collect(Collectors.toList());
    }
}
