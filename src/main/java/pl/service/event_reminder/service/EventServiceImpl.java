package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.dictionary.MonthGroups;
import pl.service.event_reminder.exception.EventException;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.repository.EventRepository;
import pl.service.event_reminder.validator.EventValidator;
import pl.service.event_reminder.web.dto.EventCreationDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final MonthGroupService monthGroupService;
    private final EventRepository eventRepository;
    private final EventValidator eventValidator;
    private final UserService userService;

    @Override
    public Event save(EventCreationDto eventCreationDto) {
        eventValidator.validateEvent(eventCreationDto);

        Event event =  Event.builder()
                .eventName(eventCreationDto.getEventName())
                .additionalNote(eventCreationDto.getAdditionalNote())
                .creationDate(ZonedDateTime.now())
                .isActive(true)
                .monthGroup(monthGroupService.findByName(eventCreationDto.getMonthGroup()))
                .user(userService.getCurrentUser())
                .nextNotifyMonth(setNextNotificationDate(eventCreationDto, userService.getCurrentUser()))
                .build();

        log.info("Creating new event for user {}. Next notification month will be: {}", userService.getCurrentUser().getEmail(),
                event.getNextNotifyMonth().getMonth().toString());

        return eventRepository.save(event);
    }

    @Override
    public Event edit(EventCreationDto eventCreationDto) {

        eventValidator.validateEvent(eventCreationDto);

        Event event = eventRepository.findById(eventCreationDto.getId())
                .orElseThrow(() -> new EventException("Cannot find event with such id"));
        event.setEventName(eventCreationDto.getEventName());
        event.setAdditionalNote(eventCreationDto.getAdditionalNote());
        event.setMonthGroup(monthGroupService.findByName(eventCreationDto.getMonthGroup()));

        return eventRepository.save(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventException("Cannot find event with such id"));
    }

    @Override
    public Set<Event> findAllByUser(User user) {
        return eventRepository.findAllByUser(user);
    }

    @Override
    public Event deactivateEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventException("Cannot find event with such id"));
        event.setActive(false);
        return eventRepository.save(event);
    }

    @Override
    public Event activateEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventException("Cannot find event with such id"));
        event.setActive(true);
        return eventRepository.save(event);
    }

    @Override
    public void setNotificationDateAfterEmailSent(Set<Event> events) {
        events.forEach(event -> {
            event.setNextNotifyMonth(ZonedDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth()));
            eventRepository.save(event);
        });
    }

    private ZonedDateTime setNextNotificationDate(EventCreationDto eventCreationDto, User user) {
        MonthGroups monthGroup = MonthGroups.fromString(eventCreationDto.getMonthGroup());
        ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Europe/Warsaw"));

        if (MonthGroups.START == monthGroup) {
            return getMonth(now, user.getStartDay());
        } else if (MonthGroups.HALF == monthGroup) {
            return getMonth(now, user.getHalfDay());
        }else {
            return getMonth(now, user.getEndDay());
        }
    }

    private ZonedDateTime getMonth(ZonedDateTime now, int day) {
        if(now.getDayOfMonth() < day) {
            return now.with(TemporalAdjusters.firstDayOfMonth());
        }else {
            return now.with(TemporalAdjusters.firstDayOfNextMonth());
        }
    }

}
