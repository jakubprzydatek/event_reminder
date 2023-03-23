package pl.service.event_reminder.web.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.service.event_reminder.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class UserRegistrationController {
    private final UserService userService;

    public String registerUserAccount(@ModelAttribute("user") UserRegistration userRegistration) {
        userService.save(userRegistration);
        return "redirect:/registration?success";
    }
}
