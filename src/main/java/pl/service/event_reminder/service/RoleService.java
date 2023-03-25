package pl.service.event_reminder.service;

import pl.service.event_reminder.model.entity.Role;

public interface RoleService {
    Role getRoleByName(String name);
}
