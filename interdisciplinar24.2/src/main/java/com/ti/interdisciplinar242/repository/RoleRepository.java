package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
