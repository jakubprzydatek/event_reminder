
package pl.service.event_reminder.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Date creationDate;
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    private MonthGroup monthGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

