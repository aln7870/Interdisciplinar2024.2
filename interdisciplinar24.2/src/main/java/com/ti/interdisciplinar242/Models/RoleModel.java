package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Role")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values {

        ADMIN(1L),
        DENTISTA(2L),
        RECEPCIONISTA(3L),
        USER(4L);

        final long id;

        Values(long id) {
            this.id = id;
        }

        public long id() {
            return id;
        }
    }


}
