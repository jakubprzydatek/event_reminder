
package pl.service.event_reminder.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String additionalNote;
    private ZonedDateTime creationDate;
    private boolean isActive;
    private ZonedDateTime nextNotifyMonth;
    @ManyToOne(fetch = FetchType.EAGER)
    private MonthGroup monthGroup;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}

