package pl.service.event_reminder.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.service.event_reminder.dictionary.MonthGroups;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.repository.EventRepository;
import pl.service.event_reminder.model.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EnableAsync
@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationChecker {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final NotificationSender notificationSender;

    @Async
    @Scheduled(fixedRate = 60000)
    public void checkEventsToNotify(){
        log.info("Events notification check...");
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            Set<Event> eventsToNotify = prepareEventsToNotifyForUser(user);
            if(!CollectionUtils.isEmpty(eventsToNotify)) {
                log.info("Events to notify has been found for user {}", user.getEmail());
                notificationSender.sendEmail(user, eventsToNotify);
            }
        });
    }

    private Set<Event> prepareEventsToNotifyForUser(User user) {
        Set<Event> events = eventRepository.findAllByUser(user);

        return events.stream().filter(this::shouldBeSent).collect(Collectors.toSet());
    }

    private boolean shouldBeSent(Event event) {
        boolean isCurrentMonth = LocalDate.now().getMonthValue() >= event.getNextNotifyMonth().getMonthValue();
        boolean isNotificationDay = false;

        if (MonthGroups.START == MonthGroups.fromString(event.getMonthGroup().getMonthGroup())) {
            isNotificationDay = true;
        } else if (MonthGroups.HALF == MonthGroups.fromString(event.getMonthGroup().getMonthGroup())) {
            if (LocalDate.now().getDayOfMonth() >= 15) {
                isNotificationDay = true;
            }
        } else if (MonthGroups.END == MonthGroups.fromString(event.getMonthGroup().getMonthGroup())) {
            if (LocalDate.now().getDayOfMonth() >= 25) {
                isNotificationDay = true;
            }
        }

        return event.isActive() && isCurrentMonth && isNotificationDay;
    }
}
