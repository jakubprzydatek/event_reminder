
package pl.service.event_reminder.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Event {
    @Id
    private Long id;
    private String eventName;
    private Date creationDate;
    private boolean isActive;
    private Date nextRemindDate;
    private Long eventGroupId;
    private Long userId;
}

