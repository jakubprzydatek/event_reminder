package pl.service.event_reminder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EventReminderApplication {
    @Value("${spring.datasource.url}")
    private String ENV_VARIABLE = null;

    public static void main(String[] args) {
        SpringApplication.run(EventReminderApplication.class, args);
    }

    @PostMapping("/hi")
    public String test(){
        return ENV_VARIABLE;
    }

}
