
package pl.service.event_reminder.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
    private LocalDate creationDate;
    private boolean isActive;
    private LocalDate nextNotifyMonth;
    @ManyToOne(fetch = FetchType.EAGER)
    private MonthGroup monthGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

