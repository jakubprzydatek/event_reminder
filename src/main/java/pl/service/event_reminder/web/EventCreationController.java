package pl.service.event_reminder.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.service.event_reminder.exception.EventValidationException;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.service.EventService;
import pl.service.event_reminder.web.dto.EventCreationDto;
import pl.service.event_reminder.web.dto.EventDisplayDto;

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

    @ExceptionHandler(value = {EventValidationException.class})
    public ModelAndView handleValidationException(EventValidationException ex) {
        ModelAndView modelAndView = new ModelAndView("create_event");
        modelAndView.addObject("event", new EventDisplayDto());
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}
