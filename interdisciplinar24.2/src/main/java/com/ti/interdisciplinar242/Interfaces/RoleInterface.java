package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleInterface extends JpaRepository<RoleModel, Long> {
}
