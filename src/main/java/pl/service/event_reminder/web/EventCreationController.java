package pl.service.event_reminder.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.service.event_reminder.service.EventService;
import pl.service.event_reminder.web.dto.EventCreationDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/createevent")
public class EventCreationController {
    private final EventService eventService;

    @GetMapping
    public String showCreationForm() {
        return "create_event";
    }

    @PostMapping
    public String createEvent(@ModelAttribute("event") EventCreationDto eventCreationDto) {
        eventService.save(eventCreationDto);
        return "redirect:/createevent?success";
    }

    @ModelAttribute("event")
    public EventCreationDto eventCreationDto() {
        return new EventCreationDto();
    }
}
