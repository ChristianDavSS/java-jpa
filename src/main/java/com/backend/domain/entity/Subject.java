package com.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Subject entity: Represents a database table/document structure
 * Using builder design pattern
 * */
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer credits;

    public Subject() {}

    public Subject(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.credits = builder.credits;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCredits() {
        return this.credits;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder inner class useful for the design pattern
     * */
    public static class Builder {
        private Long id;
        private String name;
        private Integer credits;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder credits(Integer credits) {
            this.credits = credits;
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }
    }
}
