package com.example.apiplateaujeu.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GameEntity {

    @Id
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
