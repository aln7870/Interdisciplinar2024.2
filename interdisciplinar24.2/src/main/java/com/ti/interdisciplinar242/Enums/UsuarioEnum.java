package com.ti.interdisciplinar242.Enums;

public enum UsuarioEnum {
    ADMIN("admin"),
    DENTISTA("dentista"),
    RECEPCIONISTA("recepcionista");

    private String role;

     UsuarioEnum(String role){
        this.role = role;
    }
    public String getRole(){
         return role;
    }
}
