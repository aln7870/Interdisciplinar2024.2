package com.ti.interdisciplinar242.Configurations.security;

import com.ti.interdisciplinar242.Models.Role;
import com.ti.interdisciplinar242.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRolesIfNotExist();
    }

    private void createRolesIfNotExist() {
        createRoleIfNotExist(Role.values.ADMIN);
        createRoleIfNotExist(Role.values.DENTISTA);
        createRoleIfNotExist(Role.values.RECEPCIONISTA);
        createRoleIfNotExist(Role.values.USER);
    }

    private void createRoleIfNotExist(Role.values roleEnum) {
        if (roleRepository.findByName(roleEnum.name()) == null) {
            Role role = new Role();
            role.setName(roleEnum.name());
            roleRepository.save(role);
            System.out.println("Role " + roleEnum.name() + " criada.");
        } else {
            System.out.println("Role " + roleEnum.name() + " já existe.");
        }

    }

}
