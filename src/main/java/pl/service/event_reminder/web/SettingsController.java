package pl.service.event_reminder.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.service.event_reminder.exception.UserValidationException;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.service.UserService;
import pl.service.event_reminder.web.dto.UserSettingsDto;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SettingsController {

    private final UserService userService;
    @GetMapping
    public ModelAndView showUserSettingsForm() {
        User user = userService.getCurrentUser();

        UserSettingsDto userSettingsDto = UserSettingsDto.of(user);

        ModelAndView modelAndView = new ModelAndView("user_panel");
        modelAndView.addObject("userdto", userSettingsDto);

        return modelAndView;
    }

    @PostMapping
    public String editUser(@ModelAttribute("userdto") UserSettingsDto userSettingsDto) {
        userService.updateUserSettings(userSettingsDto);

        return "redirect:/?successEditSettings";
    }

    @PostMapping("/cancel")
    public String cancelEventEdit() {
        return "redirect:/";
    }

    @ModelAttribute("userdto")
    public UserSettingsDto userSettingsDto() {
        return new UserSettingsDto();
    }

    @ExceptionHandler(value = {UserValidationException.class})
    public ModelAndView handleValidationException(UserValidationException ex) {

        User user = userService.getCurrentUser();

        UserSettingsDto userSettingsDto = UserSettingsDto.of(user);

        ModelAndView modelAndView = new ModelAndView("user_panel");
        modelAndView.addObject("userdto", userSettingsDto);
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}
