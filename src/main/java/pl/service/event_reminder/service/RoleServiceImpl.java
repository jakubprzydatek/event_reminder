package pl.service.event_reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.service.event_reminder.model.entity.Role;
import pl.service.event_reminder.model.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Override
    public Role getRoleByName(String name) {
        Role role = roleRepository.findByName(name);

        if(role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }
}
