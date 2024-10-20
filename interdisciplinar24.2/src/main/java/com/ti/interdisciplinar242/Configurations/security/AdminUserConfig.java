package com.ti.interdisciplinar242.Configurations.security;

import com.ti.interdisciplinar242.Models.Role;
import com.ti.interdisciplinar242.Models.UsuarioModel;
import com.ti.interdisciplinar242.repository.RoleRepository;
import com.ti.interdisciplinar242.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createAdminIfNotExist();
    }

    private void createAdminIfNotExist() {
        Optional<UsuarioModel> userAdmin = usuarioRepository.findByLogin("ADMIN");

        if (userAdmin.isEmpty()) {
            Role roleAdmin = roleRepository.findByName("ADMIN");
            if (roleAdmin == null) {
                roleAdmin = new Role();
                roleAdmin.setName("ADMIN");
                roleRepository.save(roleAdmin);
            }

            UsuarioModel admin = new UsuarioModel();
            admin.setNome("Adm do Sistema");
            admin.setLogin("ADMIN");
            admin.setSenha(passwordEncoder.encode("123"));
            admin.setRoles(Set.of(roleAdmin));
            usuarioRepository.save(admin);
            System.out.println("ADMIN do sistema criado.");
        } else {
            System.out.println("ADMIN já existe no sistema");
        }
    }
}
