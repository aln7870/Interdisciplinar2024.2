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
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;



    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.values.ADMIN.name());

        var userAdmin = usuarioRepository.findByLogin("ADMIN");

        userAdmin.ifPresent(
                //era pra essa bosta inserir um ADMIN no banco mas n ta indo, ent tem que inserir um admin na tab role pra o codigo funcionar tranquilo.
                // role id = 1, name = ADMIN;
                (user) -> { System.out.println("admin já existe");
                }
        );
   /*     if (!userAdmin.equals(null)) {
                var user = new UsuarioModel();
                user.setLogin("ADMIN");
                user.setSenha(passwordEncoder.encode("123"));
                user.setRoles(Set.of(roleAdmin));
                usuarioRepository.save(user);
            System.out.println("adm criado😎");
            }*/
    }
}
