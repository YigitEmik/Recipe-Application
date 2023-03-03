package ca.georgebrown.recipeapplication.service.impl;


import ca.georgebrown.recipeapplication.model.Role;
import ca.georgebrown.recipeapplication.repository.RoleRepository;
import ca.georgebrown.recipeapplication.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        String userRole = "STANDART_USER";
        Optional<Role> role = roleRepository.findByName(userRole);

        if (role.isEmpty()){
            Role newRole = new Role();
            newRole.setName(userRole);
            roleRepository.save(newRole);
            role = roleRepository.findByName(userRole);
        }
        return role.orElseThrow();
    }
}
