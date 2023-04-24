package pl.service.event_reminder.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.service.event_reminder.exception.EventValidationException;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.service.EventService;
import pl.service.event_reminder.web.dto.EventCreationDto;
import pl.service.event_reminder.web.dto.EventDisplayDto;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/edit")
public class EditController {

    private final EventService eventService;
    @PostMapping("/{id}")
    public ModelAndView showEditEventForm(@PathVariable("id") String id) {
        Event event = eventService.findById(Long.parseLong(id));
        EventDisplayDto eventDisplayDto = EventDisplayDto.map(event);

        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("event", eventDisplayDto);

        return modelAndView;
    }

    @PostMapping
    public String editEvent(@ModelAttribute("event") EventCreationDto eventCreationDto) {
        eventService.edit(eventCreationDto);

        return "redirect:/?successEdit";
    }

    @PostMapping("/cancel")
    public String cancelEventEdit() {
        return "redirect:/";
    }

    @ExceptionHandler(value = {EventValidationException.class})
    public ModelAndView handleValidationException(EventValidationException ex) {
        Event event = eventService.findById(ex.getEventId());
        EventDisplayDto eventDisplayDto = EventDisplayDto.map(event);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("event", eventDisplayDto);
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}
