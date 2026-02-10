package com.backend.domain.entity;

import jakarta.persistence.*;

/**
 * Takes entity: intermediate table that connects Student and Subject as a many-to-many relationship
 * Uses a composited primary key
 * */
@Entity
public class Takes {
    // Embed the primary key
    @EmbeddedId
    private TakesPK id;

    // set up the primary keys
    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("subject_id")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Takes() {}

    public Takes(Builder builder) {
        this.id = builder.takesPK;
        this.student = builder.student;
        this.subject = builder.subject;
    }

    public TakesPK getId() {
        return this.id;
    }

    public Student getStudent() {
        return this.student;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Takes{" +
                "id=" + id +
                ", student=" + student +
                ", subject=" + subject +
                '}';
    }

    public static class Builder {
        private TakesPK takesPK;
        private Student student;
        private Subject subject;

        public Builder takesPK(TakesPK takesPK) {
            this.takesPK = takesPK;
            return this;
        }

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Takes build() {
            return new Takes(this);
        }
    }
}
