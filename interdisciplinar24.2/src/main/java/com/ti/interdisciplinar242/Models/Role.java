package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String name;

    public enum values{
        ADMIN(1L),
        DENTISTA(2L),
        RECEPCIONISTA(3L),
        USER(4L);

        Long roleId;

        values(Long roleId) {
            this.roleId = roleId;
        }

        public long id(){
            return roleId;
        }
    }


}
