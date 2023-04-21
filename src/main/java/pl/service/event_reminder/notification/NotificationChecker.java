package pl.service.event_reminder.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.repository.EventRepository;
import pl.service.event_reminder.model.repository.UserRepository;

import java.util.List;
import java.util.Set;

@EnableAsync
@RequiredArgsConstructor
public class NotificationChecker {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync(){
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            Set<Event> eventsToNotify = prepareEventsToNotifyForUser(user);


        });
    }

    private Set<Event> prepareEventsToNotifyForUser(User user) {
        return eventRepository.findAllByUser(user);
    }
}
