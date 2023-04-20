package pl.service.event_reminder.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.service.EventService;
import pl.service.event_reminder.web.dto.EventCreationDto;
import pl.service.event_reminder.web.dto.EventDisplayDto;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {
    private final EventService eventService;
    @GetMapping
    public ModelAndView showAll() {
        User user = eventService.getCurrentUser();
        log.info("Showing all events for user {}", user.getEmail());
        List<EventDisplayDto> events = EventDisplayDto.mapEventsToDto(eventService.findAllByUser(user));
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @PostMapping("deactivate/{id}")
    public String deactivateEvent(@PathVariable("id") String id){
        eventService.deactivateEvent(Long.parseLong(id));
        return "redirect:/";
    }

    @PostMapping("activate/{id}")
    public String activateEvent(@PathVariable("id") String id){
        eventService.activateEvent(Long.parseLong(id));
        return "redirect:/";
    }

    @ModelAttribute("event")
    public EventCreationDto eventCreationDto() {
        return new EventCreationDto();
    }
}
