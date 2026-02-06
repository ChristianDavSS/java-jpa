package com.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Student entity: Database table/document structure.
 * Using builder design pattern
 * */
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String firstName;
    private Integer age;

    public Student() {}

    public Student(Builder builder) {
        this.id = builder.id;
        this.surname = builder.surname;
        this.firstName = builder.firstName;
        this.age = builder.age;
    }

    public Long getId() {
        return this.id;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Integer getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder inner class useful for the design pattern
     * */
    public static class Builder {
        private Long id;
        private String surname;
        private String firstName;
        private Integer age;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
