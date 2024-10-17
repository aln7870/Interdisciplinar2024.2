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
        var roleAdmin = roleRepository.findByName("ADMIN");
        var userAdmin = usuarioRepository.findByLogin("ADMIN");

        if (userAdmin == null) {
            UsuarioModel admin = new UsuarioModel();
            admin.setLogin("ADMIN");
            admin.setSenha(passwordEncoder.encode("123"));  // Certifique-se de definir uma senha
            admin.setRoles(Set.of(roleAdmin));
            usuarioRepository.save(admin);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
