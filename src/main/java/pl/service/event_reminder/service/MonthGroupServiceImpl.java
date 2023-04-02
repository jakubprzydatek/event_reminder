package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.model.entity.MonthGroup;
import pl.service.event_reminder.model.repository.MonthGroupRepository;

@Service
@RequiredArgsConstructor
public class MonthGroupServiceImpl implements MonthGroupService{
    private final MonthGroupRepository monthGroupRepository;

    @Override
    public MonthGroup findByName(String monthGroupName) {
        MonthGroup monthGroup = monthGroupRepository.findByMonthGroup(monthGroupName);

        if(monthGroup == null) {
            monthGroup = monthGroupRepository.save(new MonthGroup(monthGroupName));
        }

        return monthGroup;
    }
}
