package pl.service.event_reminder.validator;

import org.springframework.stereotype.Component;
import pl.service.event_reminder.exception.UserValidationException;
import pl.service.event_reminder.web.dto.UserSettingsDto;

@Component
public class UserValidator {

    private static final String START_DAY_MESSAGE_ERROR = "Dzień powiadomień dla początku miesiąca nie może być mniejszy od 1 i większy od 10";
    private static final String HALF_DAY_MESSAGE_ERROR = "Dzień powiadomień dla połowy miesiąca nie może być mniejszy od 10 i większy od 23";
    private static final String END_DAY_MESSAGE_ERROR = "Dzień powiadomień dla końca miesiąca nie może być mniejszy od 23 i większy od 30";

    public void validateUserSettings(UserSettingsDto userSettingsDto) {
        validateStartDay(Integer.parseInt(userSettingsDto.getStartDay()));
        validateHalfDay(Integer.parseInt(userSettingsDto.getHalfDay()));
        validateEndDay(Integer.parseInt(userSettingsDto.getEndDay()));
    }

    private void validateStartDay(int day) {
        if(day < 1 || day > 10) throw new UserValidationException(START_DAY_MESSAGE_ERROR);
    }

    private void validateHalfDay(int day) {
        if(day < 10 || day > 23) throw new UserValidationException(HALF_DAY_MESSAGE_ERROR);
    }

    private void validateEndDay(int day) {
        if(day < 23 || day > 30) throw new UserValidationException(END_DAY_MESSAGE_ERROR);
    }
}
