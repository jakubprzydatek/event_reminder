package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.EventException;
import pl.service.event_reminder.authentication.IAuthenticationFacade;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.model.repository.EventRepository;
import pl.service.event_reminder.web.dto.EventCreationDto;

import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final MonthGroupService monthGroupService;
    private final UserService userService;
    private final IAuthenticationFacade authenticationFacade;
    private final EventRepository eventRepository;
    @Override
    public Event save(EventCreationDto eventCreationDto) {

        Event event =  Event.builder()
                .eventName(eventCreationDto.getEventName())
                .additionalNote(eventCreationDto.getAdditionalNote())
                .creationDate(new Date())
                .isActive(true)
                .monthGroup(monthGroupService.findByName(eventCreationDto.getMonthGroup()))
                .user(getCurrentUser())
                .build();

        return eventRepository.save(event);
    }

    @Override
    public Set<Event> findAllByUser(User user) {
        return eventRepository.findAllByUser(user);
    }

    public User getCurrentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();

            return userService.findByEmail(userName);
        }else {
            throw new AuthenticationServiceException("Błąd autoryzacji");
        }
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

}
