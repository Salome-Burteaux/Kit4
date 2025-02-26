package com.example.apiplateaujeu.Controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public final class UserDto {
    public final @NotEmpty String id;
    public final @NotEmpty @Email String email;

    public UserDto(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}

