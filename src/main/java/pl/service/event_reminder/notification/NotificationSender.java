package pl.service.event_reminder.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.service.event_reminder.model.entity.Event;
import pl.service.event_reminder.model.entity.User;
import pl.service.event_reminder.service.EventService;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSender {
    private final JavaMailSender emailSender;
    private final EventService eventService;

    @Value("${spring.mail.username}")
    private String emailUsername;

    private static final String NOTIFY_SUBJECT = "PRZYPOMNIENIE O ZBLIŻAJĄCYCH SIĘ WYDARZENIACH";

    public void sendEmail(User user, Set<Event> events) {
        String emailAddress = user.getEmail();
        String message = prepareMessage(events);

        sendMessage(emailAddress, NOTIFY_SUBJECT, message);

        eventService.setNotificationDateAfterEmailSent(events);
    }

    private String prepareMessage(Set<Event> events) {
        StringBuilder message = new StringBuilder();

        events.forEach(event -> {
            message.append(event.getEventName()).append(" ").append(event.getAdditionalNote()).append("\n");
        });

        return message.toString();
    }

    private void sendMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        log.info("Sending an email to {} - with message {}", to, text);
        message.setFrom(emailUsername);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
