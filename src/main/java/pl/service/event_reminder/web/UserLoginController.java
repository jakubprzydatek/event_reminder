package pl.service.event_reminder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    @GetMapping("login")
    public String login() {
        return "login";
    }
}

