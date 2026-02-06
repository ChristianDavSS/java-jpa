package com.backend.domain.entity;

import jakarta.persistence.*;

/**
 * Takes entity: intermediate table that connects Student and Subject as a many-to-many relationship
 * Uses a composited primary key
 * */
@Entity
public class Takes {
    @EmbeddedId
    private TakesPK id;

    public Takes() {}

    public Takes(TakesPK id) {
        this.id = id;
    }

    public TakesPK getId() {
        return this.id;
    }
}
